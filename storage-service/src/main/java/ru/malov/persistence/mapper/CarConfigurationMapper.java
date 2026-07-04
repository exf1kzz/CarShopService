package ru.malov.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.malov.domain.model.car.CarConfiguration;
import ru.malov.domain.model.component.ComponentOption;
import ru.malov.persistence.entity.car.CarConfigurationEntity;
import ru.malov.persistence.entity.car.CarConfigurationOptionEntity;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CarConfigurationMapper {

    private final CarModelMapper carModelMapper;
    private final ComponentOptionMapper optionMapper;

    public CarConfiguration toDomain(CarConfigurationEntity entity) {

        Map<UUID, ComponentOption> options = new HashMap<>();

        for (CarConfigurationOptionEntity option : entity.getSelectedOptions()) {
            options.put(
                    option.getComponentId(),
                    optionMapper.toDomain(option.getOption())
            );
        }

        return new CarConfiguration(
                carModelMapper.toDomain(entity.getCarModel()),
                options,
                entity.getTotalPrice()
        );
    }

    public CarConfigurationEntity toEntity(CarConfiguration domain) {

        CarConfigurationEntity entity = new CarConfigurationEntity();

        entity.setCarModel(carModelMapper.toEntity(domain.getCarModel()));
        entity.setTotalPrice(domain.getTotalPrice());

        List<CarConfigurationOptionEntity> options = new ArrayList<>();

        for (var entry : domain.getSelectOptions().entrySet()) {

            CarConfigurationOptionEntity option = new CarConfigurationOptionEntity();

            option.setComponentId(entry.getKey());

            option.setOption(optionMapper.toEntity(entry.getValue()));

            option.setConfiguration(entity);

            options.add(option);
        }

        entity.setSelectedOptions(options);

        return entity;
    }
}
