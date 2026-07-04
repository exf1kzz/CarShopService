package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.component.Wheels;
import ru.malov.persistence.mapper.ComponentMapper;
import ru.malov.persistence.repository.WheelsJpaRepository;
import ru.malov.repository.abstractions.WheelsRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WheelsRepositoryAdapter implements WheelsRepository {

    private final WheelsJpaRepository jpaRepository;
    private final ComponentMapper mapper;

    @Override
    public Wheels Save(Wheels wheels) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(wheels)));
    }

    @Override
    public Wheels FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Wheels> FindAll() {
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
