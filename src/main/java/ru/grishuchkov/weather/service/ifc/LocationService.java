package ru.grishuchkov.weather.service.ifc;

import ru.grishuchkov.weather.entity.Location;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface LocationService {

    @Deprecated(since = "Only for testing")
    public List<Location> getLocationsByNameInMemory(String name);

    public List<Location> getLocationsByName(String name) throws URISyntaxException, IOException, InterruptedException;
}
