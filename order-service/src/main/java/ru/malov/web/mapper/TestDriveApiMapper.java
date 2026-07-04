package ru.malov.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.malov.domain.model.order.TestDriveRequest;
import ru.malov.web.dto.request.TestDriveRequestDto;
import ru.malov.web.dto.response.TestDriveResponseDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestDriveApiMapper {

    TestDriveRequest toDomain(TestDriveRequestDto dto);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "carId", source = "carId")
    TestDriveResponseDto toDto(TestDriveRequest dto);

    List<TestDriveResponseDto> toDto(List<TestDriveRequest> requests);
}
