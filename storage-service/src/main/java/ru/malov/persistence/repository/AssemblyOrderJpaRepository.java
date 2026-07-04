package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.assembly.AssemblyOrderEntity;

import java.util.UUID;

public interface AssemblyOrderJpaRepository extends JpaRepository<AssemblyOrderEntity,  UUID> {
}
