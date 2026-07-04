package ru.malov.service;

import org.springframework.stereotype.Service;
import ru.malov.domain.exeption.DomainValidationException;
import ru.malov.domain.exeption.IncompatibleComponentException;
import ru.malov.domain.model.car.CarConfiguration;
import ru.malov.domain.model.car.CarModel;
import ru.malov.domain.model.component.BaseComponent;
import ru.malov.domain.model.component.ComponentOption;
import ru.malov.domain.model.component.ConfigurationRequest;
import ru.malov.repository.abstractions.CarModelRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ConfiguratorService {

    private final CarModelRepository _carModelRepository;
    private final PriceCalculator _priceCalculator;

    public ConfiguratorService(CarModelRepository carModelRepository, PriceCalculator priceCalculator) {
        this._carModelRepository = carModelRepository;
        this._priceCalculator = priceCalculator;
    }

    public CarConfiguration BuildConfiguration(ConfigurationRequest request) {
        CarModel carModel = _carModelRepository.FindById(request.getCarModelId());
        Map<UUID, ComponentOption> selectOptions = new HashMap<>();

        ProcessComponent(carModel.getWheels(), request, selectOptions, carModel);
        ProcessComponent(carModel.getTransmission(), request, selectOptions, carModel);
        ProcessComponent(carModel.getInterior(), request, selectOptions, carModel);
        ProcessComponent(carModel.getSteeringWheel(), request, selectOptions, carModel);

        BigDecimal totalPrice = _priceCalculator.CalculateTotalPrice(carModel.getPrice(), selectOptions.values().stream().toList());

        return new CarConfiguration(carModel, selectOptions, totalPrice);
    }

    private void ProcessComponent(BaseComponent component, ConfigurationRequest request, Map<UUID, ComponentOption> selectOptions, CarModel model) {
        UUID componentId = component.getId();
        UUID selectOptionId = request.getSelectedOptionsByComponentId().get(componentId);

        if (selectOptionId == null) {
            throw new DomainValidationException("Component is required" + component.getId());
        }

        ComponentOption selectOption = component.getAvailableOptions().stream()
                .filter(option -> option.getId().equals(selectOptionId))
                .findFirst()
                .orElseThrow(() -> new DomainValidationException("Component " + componentId + " is not available"));

        if (!selectOption.getCompatibleCarModelsIds().contains(model.getId())) {
            throw new IncompatibleComponentException("Component " + componentId + " is not compatible with " + model.getId());
        }

        selectOptions.put(componentId, selectOption);
    }
}
