package ru.grishuchkov.weather.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    private String name;
    private String country;
    private String state;

    private double lat;
    private double lon;
}
