package ru.grishuchkov.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.dto.LocationSearchDto;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("locationSearchDto", new LocationSearchDto());

        return "main";
    }

    @PostMapping("/main")
    public String returnMainPage() {
        return "redirect:main";
    }
}
