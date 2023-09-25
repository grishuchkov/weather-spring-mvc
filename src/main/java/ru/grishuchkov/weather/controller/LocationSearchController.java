package ru.grishuchkov.weather.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.grishuchkov.weather.dto.LocationSearchDto;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.service.ifc.LocationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/search-location")
public class LocationSearchController {

    LocationService locationService;

    @Autowired
    public LocationSearchController(LocationService locationService) {
        this.locationService = locationService;
    }

    @SneakyThrows
    @PostMapping
    public String searchLocation(@ModelAttribute @Valid LocationSearchDto locationSearchDto,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "main";
        }

        List<Location> locationsByName = locationService.getLocationsByName(locationSearchDto.getLocationName());

        model.addAttribute("locations", locationsByName);
        return "search-location";
    }

    @PostMapping("/add")
    public String addLocation(@ModelAttribute Location location){

        locationService.setNewLocation(location);

        return "redirect:/main";
    }
}
