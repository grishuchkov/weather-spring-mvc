package ru.grishuchkov.weather.dto;

import javax.validation.constraints.Size;

public class LocationSearchDto {
    @Size(min = 1, message = "The location name is short")
    private String locationName;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
