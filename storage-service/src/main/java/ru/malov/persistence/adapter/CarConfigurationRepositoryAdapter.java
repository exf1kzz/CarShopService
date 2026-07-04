package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.car.CarConfiguration;
import ru.malov.persistence.mapper.CarConfigurationMapper;
import ru.malov.persistence.repository.CarConfigurationJpaRepository;
import ru.malov.repository.abstractions.CarConfigurationRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CarConfigurationRepositoryAdapter implements CarConfigurationRepository {

    private final CarConfigurationJpaRepository jpaRepository;
    private final CarConfigurationMapper mapper;

    @Override
    public CarConfiguration Save(CarConfiguration configuration) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(configuration)));
    }

    @Override
    public CarConfiguration FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<CarConfiguration> FindAll() {
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
