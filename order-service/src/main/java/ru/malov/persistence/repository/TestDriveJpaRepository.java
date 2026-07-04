package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.order.TestDriveRequestEntity;

import java.util.UUID;

public interface TestDriveJpaRepository extends JpaRepository<TestDriveRequestEntity, UUID> {
}
