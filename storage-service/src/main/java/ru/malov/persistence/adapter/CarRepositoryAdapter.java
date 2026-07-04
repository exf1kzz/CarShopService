package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.car.Car;
import ru.malov.persistence.mapper.CarMapper;
import ru.malov.persistence.repository.CarJpaRepository;
import ru.malov.repository.abstractions.CarRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarRepositoryAdapter implements CarRepository {

    private final CarJpaRepository jpaRepository;
    private final CarMapper mapper;

    @Override
    public Car Save(Car car) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(car)));
    }

    @Override
    @Transactional(readOnly = true)
    public Car FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> FindAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void Delete(UUID id) {
        jpaRepository.deleteById(id);
    }
}
