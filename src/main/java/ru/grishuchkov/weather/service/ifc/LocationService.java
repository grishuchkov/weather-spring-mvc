package ru.grishuchkov.weather.service.ifc;

import ru.grishuchkov.weather.entity.Location;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface LocationService {

    public List<Location> getLocationsByName(String name) throws URISyntaxException, IOException, InterruptedException;

    void setNewLocation(Location location);
}
