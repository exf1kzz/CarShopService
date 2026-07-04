package ru.malov.grpc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.malov.domain.exeption.StorageUnavailableException;


import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers(disabledWithoutDocker = true)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ru.malov.Application.class)
class StorageCarsGrpcClientIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("order_it_db")
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

        registry.add("grpc.client.storage-service.address", () -> "static://localhost:6553");
        registry.add("grpc.client.storage-service.negotiationType", () -> "PLAINTEXT");

        registry.add("spring.rabbitmq.listener.simple.auto-startup", () -> "false");
        registry.add("spring.rabbitmq.listener.direct.auto-startup", () -> "false");
        registry.add("spring.task.scheduling.enabled", () -> "false");
    }

    @Test
    void getAvailableCars_whenStorageUnavailable_shouldThrowStorageUnavailableException(
            @Autowired StorageCarsGrpcClient client
    ) {
        assertThrows(StorageUnavailableException.class, client::getAvailableCars);
    }
}