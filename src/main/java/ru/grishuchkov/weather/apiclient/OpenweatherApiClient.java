package ru.grishuchkov.weather.apiclient;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.grishuchkov.weather.apiclient.ifc.WeatherApiClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class OpenweatherApiClient implements WeatherApiClient {

    @Value("${openweather.token}")
    private String API_KEY;

    @Value("${openweather.search-location-by-name-url}")
    private String SEARCH_LOCATION_BY_NAME_URL;

    @Value("${openweather.get-weather-url}")
    private String GET_WEATHER_URL;

    @Override
    @SneakyThrows
    public HttpResponse<String> findLocationsByName(String name){

        String urlencoded = UriComponentsBuilder.fromHttpUrl(SEARCH_LOCATION_BY_NAME_URL)
                .queryParam("q", name)
                .queryParam("limit", 10)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .encode()
                .toUriString();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(urlencoded))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    @Override
    @SneakyThrows
    public HttpResponse<String> getWeatherByCoordinates(double lat, double lon) {

        String urlencoded = UriComponentsBuilder.fromHttpUrl(GET_WEATHER_URL)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .encode()
                .toUriString();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(urlencoded))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
