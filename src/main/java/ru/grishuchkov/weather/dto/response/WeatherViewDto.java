package ru.grishuchkov.weather.dto.response;

import lombok.*;

@Builder
@Getter
public class WeatherViewDto {
    private String name;
    private String country;
    private String state;
    private double lat;
    private double lon;
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
