package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.car.CarModel;
import ru.malov.domain.model.component.*;
import ru.malov.persistence.entity.car.CarModelEntity;
import ru.malov.persistence.mapper.CarModelMapper;
import ru.malov.persistence.mapper.ComponentMapper;
import ru.malov.persistence.repository.CarModelJpaRepository;
import ru.malov.repository.abstractions.CarModelRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarModelRepositoryAdapter implements CarModelRepository {

    private final CarModelJpaRepository jpaRepository;
    private final CarModelMapper mapper;
    private final ComponentMapper componentMapper;

    @Override
    public CarModel Save(CarModel carModel) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(carModel)));
    }

    @Override
    public CarModel FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<CarModel> FindAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void Delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public CarModel AddComponent(UUID id, BaseComponent component) {
        CarModelEntity entity = jpaRepository.findById(id).orElseThrow();

        if (component instanceof Wheels wheels) {
            entity.setWheels(componentMapper.toEntity(wheels));
        }

        if (component instanceof Transmission transmission) {
            entity.setTransmission(componentMapper.toEntity(transmission));
        }

        if (component instanceof Interior interior) {
            entity.setInterior(componentMapper.toEntity(interior));
        }

        if (component instanceof SteeringWheel steeringWheel) {
            entity.setSteeringWheel(componentMapper.toEntity(steeringWheel));
        }

        return mapper.toDomain(jpaRepository.save(entity));
    }
}
