package ru.malov.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.malov.grpc.cars.v1.CarAvailabilityServiceGrpc;
import ru.malov.grpc.cars.v1.GetAvailableCarsRequest;
import ru.malov.grpc.cars.v1.GetAvailableCarsResponse;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest
class CarAvailabilityGrpcServiceIntegrationTest {

    private static final int TEST_GRPC_PORT = 9191;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("storage_it_db")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
        registry.add("spring.liquibase.change-log", () -> "classpath:db/changelog/master.xml");
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () -> "http://dummy");
        registry.add("grpc.server.port", () -> TEST_GRPC_PORT);
        registry.add("spring.rabbitmq.listener.simple.auto-startup", () -> "false");
        registry.add("spring.rabbitmq.listener.direct.auto-startup", () -> "false");
    }

    private ManagedChannel channel;

    @AfterEach
    void tearDown() {
        if (channel != null) channel.shutdownNow();
    }

    @Test
    void getAvailableCars_shouldReturnOnlyAvailableStatuses() {
        channel = ManagedChannelBuilder.forAddress("localhost", TEST_GRPC_PORT)
                .usePlaintext()
                .build();

        CarAvailabilityServiceGrpc.CarAvailabilityServiceBlockingStub stub =
                CarAvailabilityServiceGrpc.newBlockingStub(channel);

        GetAvailableCarsResponse response =
                stub.getAvailableCars(GetAvailableCarsRequest.newBuilder().build());

        assertFalse(response.getCarsList().isEmpty());
        Set<String> allowed = Set.of("AVAILABLE", "TEST_DRIVE_AVAILABLE");
        assertTrue(response.getCarsList().stream().allMatch(c -> allowed.contains(c.getStatus())));
    }
}