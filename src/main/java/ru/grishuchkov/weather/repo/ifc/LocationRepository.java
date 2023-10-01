package ru.grishuchkov.weather.repo.ifc;

import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.entity.User;

import java.util.List;

public interface LocationRepository {

    void saveForUserLogin(Location location, String userLogin);

    List<Location> findByUserLogin(String login);

    void deleteByCoordinatesAndUserLogin(double lat, double lon, String login);

}
