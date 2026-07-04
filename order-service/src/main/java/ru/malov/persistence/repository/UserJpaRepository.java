package ru.malov.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malov.persistence.entity.user.UserEntity;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
