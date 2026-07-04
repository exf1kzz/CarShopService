package ru.malov.persistence.entity.assembly;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.malov.persistence.entity.BaseEntity;

import java.util.UUID;

@Entity
@Table(name = "assembly_orders")
@Getter
@Setter
public class AssemblyOrderEntity extends BaseEntity {

    @Column(nullable = false)
    private UUID sourceOrderId;

    @Column(nullable = false)
    private String status;

    @Column
    private String sourceOrderType;

    @Column
    private String traceId;
}
