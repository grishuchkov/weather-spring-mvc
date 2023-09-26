package ru.grishuchkov.weather.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.grishuchkov.weather.service.ifc.WeatherApiClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class OpenweatherApiClient implements WeatherApiClient {

    @Value("${openweather.token}")
    private String API_KEY;

    @Value("${openweather.url-for-search-location-by-name}")
    private String URL_FOR_SEARCH_LOCATION_BY_NAME;

    @Override
    @SneakyThrows
    public HttpResponse<String> findLocationsByName(String name){

        String urlencoded = UriComponentsBuilder.fromHttpUrl(URL_FOR_SEARCH_LOCATION_BY_NAME)
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
    public HttpResponse<String> getWeatherByCoordinates(double lat, double lon) {
        return null;
    }
}
