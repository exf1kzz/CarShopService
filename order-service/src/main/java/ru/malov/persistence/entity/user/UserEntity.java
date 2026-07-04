package ru.malov.persistence.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String username;
}
