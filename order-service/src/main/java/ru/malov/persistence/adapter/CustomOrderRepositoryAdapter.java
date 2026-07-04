package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.order.CustomOrder;
import ru.malov.persistence.mapper.CustomOrderMapper;
import ru.malov.persistence.repository.CustomOrderJpaRepository;
import ru.malov.repository.abstractions.CustomOrderRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomOrderRepositoryAdapter implements CustomOrderRepository {

    private final CustomOrderJpaRepository jpaRepository;
    private final CustomOrderMapper mapper;

    @Override
    public CustomOrder Save(CustomOrder order) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(order)));
    }

    @Override
    public CustomOrder FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<CustomOrder> FindAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void Delete(CustomOrder order) {
        jpaRepository.deleteById(order.getId());
    }
}
