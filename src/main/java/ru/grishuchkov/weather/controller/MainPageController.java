package ru.grishuchkov.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.dto.request.LocationSearchDto;
import ru.grishuchkov.weather.dto.response.WeatherViewDto;
import ru.grishuchkov.weather.service.ifc.WeatherService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainPageController {

    WeatherService weatherService;

    @Autowired
    public MainPageController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/main")
    public String getMainPage(Model model, Principal principal) {
        model.addAttribute("locationSearchDto", new LocationSearchDto());

        List<WeatherViewDto> weatherViewDtoList = weatherService.getWeatherListForUserLocations(principal.getName());

        model.addAttribute("weatherViewDtoList", weatherViewDtoList);

        return "main";
    }

    @PostMapping("/main")
    public String returnMainPage() {
        return "redirect:main";
    }
}
