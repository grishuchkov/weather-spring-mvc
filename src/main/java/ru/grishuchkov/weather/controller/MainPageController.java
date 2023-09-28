package ru.grishuchkov.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.dto.request.LocationSearchDto;
import ru.grishuchkov.weather.dto.response.WeatherViewDto;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("locationSearchDto", new LocationSearchDto());

        WeatherViewDto tmpWeatherViewDto = WeatherViewDto.builder()
                .name("Saint-Petersburg")
                .country("Russia")
                .state("Leningradskya oblast")
                .lon(59.938732)
                .lat(30.316229)
                .weatherIconCode("04d")
                .weatherDescription("overcast clouds")
                .temperature(16.66)
                .temperatureFeelsLike(16.73)
                .pressure(1017)
                .windSpeed(2)
                .sunrise("7:01:57")
                .sunset("23:00:57")
                .dateCreation("28.09.2023 9:54:24")
                .build();

        List<WeatherViewDto> weatherViewDtoList = new ArrayList<>();
        weatherViewDtoList.add(tmpWeatherViewDto);
        weatherViewDtoList.add(tmpWeatherViewDto);
        weatherViewDtoList.add(tmpWeatherViewDto);
        weatherViewDtoList.add(tmpWeatherViewDto);

        model.addAttribute("weatherViewDtoList", weatherViewDtoList);

        return "main";
    }

    @PostMapping("/main")
    public String returnMainPage() {
        return "redirect:main";
    }
}
