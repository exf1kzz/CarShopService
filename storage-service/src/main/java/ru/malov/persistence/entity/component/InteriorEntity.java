package ru.malov.persistence.entity.component;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interiors")
@Getter
@Setter
public class InteriorEntity extends BaseComponentEntity {
}
