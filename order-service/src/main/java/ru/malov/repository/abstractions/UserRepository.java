package ru.malov.repository.abstractions;

import ru.malov.domain.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    User Save(User user);

    User FindById(UUID id);

    List<User> FindAll();

    void Delete(UUID id);
}
