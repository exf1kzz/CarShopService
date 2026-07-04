package ru.malov.domain.model.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.model.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class TestDriveRequest {
    private final UUID id;
    private final User client;
    private final UUID carId;
    private final LocalDateTime date;
}
