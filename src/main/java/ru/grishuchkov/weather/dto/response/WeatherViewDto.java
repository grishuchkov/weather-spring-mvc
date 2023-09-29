package ru.grishuchkov.weather.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grishuchkov.weather.entity.Location;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherViewDto {
    private Location location;
    private String weatherIconCode;
    private String weatherDescription;
    private double temperature;
    private double temperatureFeelsLike;
    private double pressure;
    private double windSpeed;
    private String sunrise;
    private String sunset;
    private String dateCreation;
}
