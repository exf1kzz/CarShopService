package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.component.SteeringWheel;
import ru.malov.persistence.mapper.ComponentMapper;
import ru.malov.persistence.repository.SteeringWheelJpaRepository;
import ru.malov.repository.abstractions.SteeringWheelRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SteeringWheelRepositoryAdapter implements SteeringWheelRepository {

    private final SteeringWheelJpaRepository jpaRepository;
    private final ComponentMapper mapper;

    @Override
    public SteeringWheel Save(SteeringWheel steeringWheel) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(steeringWheel)));
    }

    @Override
    public SteeringWheel FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<SteeringWheel> FindAll() {
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
