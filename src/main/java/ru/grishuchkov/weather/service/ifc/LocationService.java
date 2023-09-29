package ru.grishuchkov.weather.service.ifc;

import ru.grishuchkov.weather.entity.Location;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface LocationService {

    List<Location> getLocationsByName(String name) throws URISyntaxException, IOException, InterruptedException;

    void saveNewLocationFromUser(Location location, String username);

    void deleteUserLocation(Location location, String username);
}
