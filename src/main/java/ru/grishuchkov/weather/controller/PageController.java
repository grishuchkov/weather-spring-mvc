package ru.grishuchkov.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.dto.LocationSearchDto;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.service.ifc.LocationService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    LocationService locationService;

    @Autowired
    public PageController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("locationSearchDto", new LocationSearchDto());

        return "main";
    }

    @PostMapping("/main")
    public String returnMainPage() {
        return "redirect:main";
    }

    @PostMapping("/search-location")
    public String searchLocation(@ModelAttribute @Valid LocationSearchDto locationSearchDto,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "main";
        }

        List<Location> locationsByName = locationService.getLocationsByName(locationSearchDto.getLocationName());

        model.addAttribute("locations", locationsByName);
        return "main";
    }
}
