package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.order.TestDriveRequest;
import ru.malov.persistence.mapper.TestDriveMapper;
import ru.malov.persistence.repository.TestDriveJpaRepository;
import ru.malov.repository.abstractions.TestDriveRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TestDriveRepositoryAdapter implements TestDriveRepository {

    private final TestDriveJpaRepository jpaRepository;
    private final TestDriveMapper mapper;

    @Override
    public TestDriveRequest Save(TestDriveRequest request) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(request)));
    }

    @Override
    public TestDriveRequest FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<TestDriveRequest> FindAll() {
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
