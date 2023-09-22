package ru.grishuchkov.weather.service;


import org.springframework.stereotype.Component;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.service.ifc.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationServiceImp implements LocationService {
    @Override
    public List<Location> getLocationsByName(String name) {
        List<Location> locationList = new ArrayList<>();

        List<Location> tmpLocationStorage = new ArrayList<>();
        tmpLocationStorage.add(new Location("Moscow", "RU", "Moscow", 2, 3));
        tmpLocationStorage.add(new Location("Ryazan", "RU", "Ryazan", 2, 3));
        tmpLocationStorage.add(new Location("Moscow", "BR", "Belgium", 2, 3));
        tmpLocationStorage.add(new Location("Saint-P", "RU", "Leningrad", 2, 3));
        tmpLocationStorage.add(new Location("Moscow", "CH", "Chines moscow", 2, 3));


        locationList = tmpLocationStorage.stream()
                .filter(location -> name.equals(location.getName()))
                .collect(Collectors.toCollection(ArrayList::new));

        return locationList;
    }
}
