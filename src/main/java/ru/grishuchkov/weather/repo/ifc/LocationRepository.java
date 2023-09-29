package ru.grishuchkov.weather.repo.ifc;

import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.entity.User;

import java.util.List;

public interface LocationRepository {

    void saveNewLocationByUser(Location location, User user);

    List<Location> getLocationsByUserLogin(String login);

    void deleteLocationByCoordinatesAndUserLogin(double lat, double lon, String login);

}
