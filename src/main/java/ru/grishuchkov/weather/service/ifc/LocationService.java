package ru.grishuchkov.weather.service.ifc;

import ru.grishuchkov.weather.entity.Location;

import java.util.List;

public interface LocationService {

    public List<Location> getLocationsByName(String name);
}
