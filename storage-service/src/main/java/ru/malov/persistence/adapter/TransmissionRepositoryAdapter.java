package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.component.Transmission;
import ru.malov.persistence.mapper.ComponentMapper;
import ru.malov.persistence.repository.TransmissionJpaRepository;
import ru.malov.repository.abstractions.TransmissionRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransmissionRepositoryAdapter implements TransmissionRepository {

    private final TransmissionJpaRepository jpaRepository;
    private final ComponentMapper mapper;

    @Override
    public Transmission Save(Transmission transmission) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(transmission)));
    }

    @Override
    public Transmission FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Transmission> FindAll() {
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
