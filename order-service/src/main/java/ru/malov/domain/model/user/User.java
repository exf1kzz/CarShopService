package ru.malov.domain.model.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.malov.domain.enums.UserRole;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class User {
    private final UUID id;
    private final String username;
}
