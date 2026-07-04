package ru.malov.service;

import org.springframework.stereotype.Service;
import ru.malov.domain.model.component.ComponentOption;
import ru.malov.repository.abstractions.ComponentOptionRepository;
import ru.malov.web.dto.request.CreateComponentOptionRequestDto;

import java.util.List;
import java.util.UUID;

@Service
public class ComponentOptionService {

    private final ComponentOptionRepository repository;

    public ComponentOptionService(ComponentOptionRepository repository) {
        this.repository = repository;
    }

    public List<ComponentOption> getAll() {
        return repository.FindAll();
    }

    public ComponentOption getById(UUID id) {
        return repository.FindById(id);
    }

    public ComponentOption create(CreateComponentOptionRequestDto dto) {
        ComponentOption option = new ComponentOption(
                UUID.randomUUID(),
                dto.getName(),
                dto.getAdditionalPrice(),
                dto.getCompatibleCarModelsIds()
        );

        return repository.Save(option);
    }

    public void delete(UUID id) {
        repository.Delete(id);
    }
}
