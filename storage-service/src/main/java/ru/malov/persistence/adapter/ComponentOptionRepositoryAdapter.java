package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.component.ComponentOption;
import ru.malov.persistence.mapper.ComponentOptionMapper;
import ru.malov.persistence.repository.ComponentOptionJpaRepository;
import ru.malov.repository.abstractions.ComponentOptionRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ComponentOptionRepositoryAdapter implements ComponentOptionRepository {

    private final ComponentOptionJpaRepository jpaRepository;
    private final ComponentOptionMapper mapper;

    @Override
    public ComponentOption Save(ComponentOption option) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(option)));
    }

    @Override
    public ComponentOption FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<ComponentOption> FindAll() {
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
