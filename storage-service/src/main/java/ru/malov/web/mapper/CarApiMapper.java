package ru.malov.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.malov.domain.model.car.Car;
import ru.malov.domain.model.car.CarFilter;
import ru.malov.web.dto.request.CarFilterRequestDto;
import ru.malov.web.dto.response.CarResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarApiMapper {

    @Mapping(target = "brand", source = "model.brand")
    @Mapping(target = "model", source = "model.model")
    @Mapping(target = "bodyType", source = "bodyType")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "fuelType", source = "fuelType")
    @Mapping(target = "transmissionType", source = "transmissionType")
    @Mapping(target = "driveType", source = "driveType")
    @Mapping(target = "status", source = "status")
    CarResponseDto toDto(Car car);

    List<CarResponseDto> toDto(List<Car> cars);

    CarFilter toFilter(CarFilterRequestDto dto);
}
