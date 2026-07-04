package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.component.InteriorEntity;

import java.util.UUID;

public interface InteriorJpaRepository extends JpaRepository<InteriorEntity, UUID> {
}
