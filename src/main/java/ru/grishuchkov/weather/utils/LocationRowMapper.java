package ru.grishuchkov.weather.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.grishuchkov.weather.entity.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();

        location.setLat(resultSet.getDouble("latitude"));
        location.setLon(resultSet.getDouble("longitude"));
        location.setName(resultSet.getString("name"));
        location.setCountry(resultSet.getString("country"));
        location.setState(resultSet.getString("state"));

        return location;
    }
}
