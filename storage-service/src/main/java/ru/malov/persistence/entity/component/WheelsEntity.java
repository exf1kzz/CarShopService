package ru.malov.persistence.entity.component;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wheels")
@Getter
@Setter
public class WheelsEntity extends BaseComponentEntity {
}
