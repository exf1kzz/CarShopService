package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.component.ComponentOptionEntity;

import java.util.UUID;

public interface ComponentOptionJpaRepository extends JpaRepository<ComponentOptionEntity, UUID> {
}
