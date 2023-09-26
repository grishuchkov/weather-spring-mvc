package ru.grishuchkov.weather.service.ifc;
import java.net.http.HttpResponse;

public interface WeatherApiClient {

    HttpResponse<String> findLocationsByName(String name);


    HttpResponse<String> getWeatherByCoordinates(double lat, double lon);
}
