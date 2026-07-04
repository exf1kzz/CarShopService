package ru.malov.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.malov.domain.model.order.StockOrder;
import ru.malov.persistence.mapper.StockOrderMapper;
import ru.malov.persistence.repository.StockOrderJpaRepository;
import ru.malov.repository.abstractions.StockOrderRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StockOrderRepositoryAdapter implements StockOrderRepository {

    private final StockOrderJpaRepository jpaRepository;
    private final StockOrderMapper mapper;

    @Override
    public StockOrder Save(StockOrder order) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(order)));
    }

    @Override
    public StockOrder FindById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<StockOrder> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(StockOrder order) {
        jpaRepository.deleteById(order.getId());
    }
}
