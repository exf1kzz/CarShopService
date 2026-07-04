package ru.malov.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.malov.domain.model.order.CustomOrder;
import ru.malov.domain.model.order.StockOrder;
import ru.malov.web.dto.response.CustomOrderResponseDto;
import ru.malov.web.dto.response.StockOrderResponseDto;

@Mapper(componentModel = "spring")
public interface OrderApiMapper {

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "carId", source = "carId")
    @Mapping(target = "status", expression = "java(order.getState().GetStatus().name())")
    StockOrderResponseDto toStockDto(StockOrder order);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "configurationId", source = "configurationId")
    @Mapping(target = "status", expression = "java(order.getState().GetStatus().name())")
    CustomOrderResponseDto toCustomDto(CustomOrder order);
}
