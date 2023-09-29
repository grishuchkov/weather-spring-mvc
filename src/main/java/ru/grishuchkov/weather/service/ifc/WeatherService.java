package ru.grishuchkov.weather.service.ifc;

import ru.grishuchkov.weather.dto.response.WeatherViewDto;

import java.util.List;

public interface WeatherService {

    List<WeatherViewDto> getWeatherListForUserLocations(String userLogin);
}
