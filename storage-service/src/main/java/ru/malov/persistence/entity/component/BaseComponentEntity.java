package ru.malov.persistence.entity.component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;

import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseComponentEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "base_option_id")
    private ComponentOptionEntity baseOption;

    @OneToMany
    @JoinColumn(name = "component_id")
    private List<ComponentOptionEntity> availableOptions;
}
