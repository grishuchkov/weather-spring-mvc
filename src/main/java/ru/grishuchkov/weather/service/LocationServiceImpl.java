package ru.grishuchkov.weather.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.entity.User;
import ru.grishuchkov.weather.repo.ifc.LocationRepository;
import ru.grishuchkov.weather.service.ifc.LocationService;
import ru.grishuchkov.weather.service.ifc.WeatherApiClient;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class LocationServiceImpl implements LocationService {

    private final JsonMapper jsonMapper;
    private final WeatherApiClient weatherApiClient;

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(JsonMapper jsonMapper, WeatherApiClient weatherApiClient, LocationRepository locationRepository) {
        this.jsonMapper = jsonMapper;
        this.weatherApiClient = weatherApiClient;
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Location> getLocationsByName(String name) throws IOException {

        name = prepareLocationName(name);

        HttpResponse<String> response = weatherApiClient.findLocationsByName(name);
        List<Location> resultList = jsonMapper.readValue(response.body(), new TypeReference<>() {});

        return resultList;
    }


    @Override
    public void saveNewLocationFromUser(Location location, String username) {

        locationRepository.saveNewLocationByUser(location, new User(username));
    }

    private String prepareLocationName(String name) {
        name = name.replaceAll("\\s","-");
        return name;
    }
}
