package ru.grishuchkov.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grishuchkov.weather.dto.response.WeatherViewDto;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.repo.ifc.LocationRepository;
import ru.grishuchkov.weather.service.ifc.WeatherApiClient;
import ru.grishuchkov.weather.service.ifc.WeatherService;
import ru.grishuchkov.weather.utils.HttpResponseToWeatherDtoMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    LocationRepository locationRepository;
    WeatherApiClient weatherApiClient;

    @Autowired
    public WeatherServiceImpl(LocationRepository locationRepository, WeatherApiClient weatherApiClient) {
        this.locationRepository = locationRepository;
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    public List<WeatherViewDto> getWeatherListForUserLocations(String userLogin) {
        HttpResponseToWeatherDtoMapper mapper = new HttpResponseToWeatherDtoMapper();

        List<Location> userLocations = locationRepository.getLocationsByUserLogin(userLogin);
        List<WeatherViewDto> resultWeathersList = new ArrayList<>();

        if (userLocations.isEmpty()) {
            throw new RuntimeException();
        }

        for (Location location : userLocations) {
            HttpResponse<String> weatherResponse = weatherApiClient.getWeatherByCoordinates(location.getLat(), location.getLon());
            WeatherViewDto responseWeatherDTO = mapper.map(weatherResponse);
            responseWeatherDTO.setLocation(location);
            resultWeathersList.add(responseWeatherDTO);
        }

        return resultWeathersList;
    }
}
