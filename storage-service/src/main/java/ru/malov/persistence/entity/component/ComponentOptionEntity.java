package ru.malov.persistence.entity.component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "component_options")
@Getter
@Setter
public class ComponentOptionEntity extends BaseEntity {

    private String name;

    private BigDecimal additionalPrice;

    @ElementCollection
    @CollectionTable(
            name = "component_option_models",
            joinColumns = @JoinColumn(name = "option_id")
    )
    @Column(name = "model_id")
    private Set<UUID> compatibleCarModelsIds;
}
