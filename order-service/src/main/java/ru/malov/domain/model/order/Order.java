package ru.malov.domain.model.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.model.user.User;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public abstract class Order {
    private final UUID id;
    private final User client;
    private final User manager;
}
