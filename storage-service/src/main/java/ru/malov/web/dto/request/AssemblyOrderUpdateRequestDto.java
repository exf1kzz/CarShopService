package ru.malov.web.dto.request;

import lombok.Data;

@Data
public class AssemblyOrderUpdateRequestDto {
    private String status;
    private Boolean removed;
}
