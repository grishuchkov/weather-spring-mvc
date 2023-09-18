package ru.grishuchkov.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @PostMapping("/main")
    public String getMainPage() {
        return "main";
    }
}
