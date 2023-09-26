package ru.grishuchkov.weather.repo.ifc;

import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.entity.User;

public interface LocationRepository {

    void saveNewLocationByUser(Location location, User user);
}
