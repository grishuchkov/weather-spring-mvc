package ru.grishuchkov.weather.repo.ifc;

import ru.grishuchkov.weather.entity.User;

public interface UserRepository {

    User findByLogin(String login);

    void save(User user);
}
