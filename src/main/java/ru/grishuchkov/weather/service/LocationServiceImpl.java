package ru.grishuchkov.weather.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.service.ifc.LocationService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class LocationServiceImpl implements LocationService {

    private final JsonMapper jsonMapper;

    @Autowired
    public LocationServiceImpl(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Value("${openweather.token}")
    private String API_KEY;

    @Override
    public List<Location> getLocationsByName(String name) throws URISyntaxException, IOException, InterruptedException {
        //todo: weather API Client class --impl--> OpenWeatherApiClient : getLocationsByName and getWeatherByCoordinates;

        name = name.replaceAll("\\s","-");

        final String BASE_API_URL = "http://api.openweathermap.org/geo/1.0/direct";

        String urlencoded = UriComponentsBuilder.fromHttpUrl(BASE_API_URL)
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

        List<Location> resultList = jsonMapper.readValue(response.body(), new TypeReference<>() {});

        return resultList;
    }

    @Override
    public void setNewLocation(Location location) {

    }

}
