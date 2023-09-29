package ru.grishuchkov.weather.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.grishuchkov.weather.dto.response.WeatherViewDto;

import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpResponseToWeatherDtoMapper {
    @SneakyThrows
    public WeatherViewDto map(HttpResponse<String> response){
        String responseBody = response.body();
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        WeatherViewDto weatherDto = new WeatherViewDto();

        JsonNode root = mapper.readTree(responseBody);

        JsonNode weather = root.get("weather").get(0);
        String description = weather.get("description").asText();
        String weatherIcon = weather.get("icon").asText();
        weatherDto.setWeatherDescription(description);
        weatherDto.setWeatherIconCode(weatherIcon);

        JsonNode main = root.get("main");
        double temperature = main.get("temp").asDouble();
        double feelsLike = main.get("feels_like").asDouble();
        double pressure = main.get("pressure").asDouble();
        weatherDto.setTemperature(temperature);
        weatherDto.setTemperatureFeelsLike(feelsLike);
        weatherDto.setPressure(pressure);

        JsonNode wind = root.get("wind");
        double windSpeed = wind.get("speed").asDouble();
        weatherDto.setWindSpeed(windSpeed);

        long date = root.get("dt").asLong();
        String dateCreation = dateFormat.format(new Date(date));
        weatherDto.setDateCreation(dateCreation);

        JsonNode system = root.get("sys");
        long sunriseLong = system.get("sunrise").asLong();
        long sunsetLong = system.get("sunset").asLong();

        String sunrise = dateFormat.format(new Date(sunriseLong));
        String sunset = dateFormat.format(new Date(sunsetLong));
        weatherDto.setSunrise(sunrise);
        weatherDto.setSunset(sunset);

        return weatherDto;
    }
}
