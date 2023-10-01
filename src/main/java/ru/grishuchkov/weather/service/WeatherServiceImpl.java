package ru.grishuchkov.weather.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grishuchkov.weather.dto.response.WeatherViewDto;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.repo.ifc.LocationRepository;
import ru.grishuchkov.weather.apiclient.ifc.WeatherApiClient;
import ru.grishuchkov.weather.service.ifc.WeatherService;
import ru.grishuchkov.weather.utils.HttpResponseToWeatherDtoMapper;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    @SneakyThrows
    public List<WeatherViewDto> getWeatherListForUserLocations(String userLogin) {
        HttpResponseToWeatherDtoMapper mapper = new HttpResponseToWeatherDtoMapper();

        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Location> userLocations = locationRepository.findByUserLogin(userLogin);

        List<Future<HttpResponse<String>>> futures = new ArrayList<>();
        List<WeatherViewDto> resultWeathersList = new ArrayList<>();

        for (Location loc : userLocations) {
            Future<HttpResponse<String>> future = executor.submit(() -> {
                return weatherApiClient.getWeatherByCoordinates(loc.getLat(), loc.getLon());
            });
            futures.add(future);
        }

        for (int i = 0; i < futures.size(); i++) {
            HttpResponse<String> response = futures.get(i).get();
            Location location = userLocations.get(i);

            WeatherViewDto responseWeatherDTO = mapper.map(response);
            responseWeatherDTO.setLocation(location);
            resultWeathersList.add(responseWeatherDTO);
        }

        return resultWeathersList;
    }
}
