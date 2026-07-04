package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.component.Interior;
import ru.malov.persistence.mapper.ComponentMapper;
import ru.malov.persistence.repository.InteriorJpaRepository;
import ru.malov.repository.abstractions.InteriorRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InteriorRepositoryAdapter implements InteriorRepository {

    private final InteriorJpaRepository jpaRepository;
    private final ComponentMapper mapper;

    @Override
    public Interior Save(Interior interior) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(interior)));
    }

    @Override
    public Interior FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Interior> FindAll() {
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
