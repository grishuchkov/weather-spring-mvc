package ru.grishuchkov.weather.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.service.ifc.LocationService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationServiceImp implements LocationService {

    private final JsonMapper jsonMapper;

    @Autowired
    public LocationServiceImp(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Value("${openweather.token}")
    private String apiKey;

    @Override
    public List<Location> getLocationsByName(String name) throws URISyntaxException, IOException, InterruptedException {
        //todo: make refactor!!!

        name = name.replaceAll("\\s","-");

        String uriTemplate = "http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=10&appid=%s";
        String uri = String.format(uriTemplate, name, apiKey);


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
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
