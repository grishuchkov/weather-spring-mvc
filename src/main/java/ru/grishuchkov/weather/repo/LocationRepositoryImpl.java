package ru.grishuchkov.weather.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.entity.User;
import ru.grishuchkov.weather.exception.DuplicateLocationException;
import ru.grishuchkov.weather.repo.ifc.LocationRepository;
import ru.grishuchkov.weather.utils.LocationRowMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveForUserLogin(Location location, String login) {
        String SQL = "INSERT INTO locations(user_login, name, country, state, latitude, longitude) VALUES (?,?,?,?,?,?)";

        try {
            jdbcTemplate.update(SQL, login, location.getName(), location.getCountry(), location.getState(), location.getLat(), location.getLon());
        } catch (DuplicateKeyException e) {
            throw new DuplicateLocationException("The user already has such a location");
        }
    }

    @Override
    public List<Location> findByUserLogin(String login) {

        String SQL = "SELECT name, country, state, latitude, longitude FROM locations WHERE user_login =? ORDER BY id DESC";

        List<Location> locations = jdbcTemplate.query(SQL, new Object[]{login},
                        new LocationRowMapper())
                .stream()
                .collect(Collectors.toUnmodifiableList());

        return locations;
    }

    @Override
    public void deleteByCoordinatesAndUserLogin(double lat, double lon, String login) {
        String SQL = "DELETE FROM locations WHERE latitude = ? and longitude = ? and user_login = ?";

        jdbcTemplate.update(SQL, lat, lon, login);
    }
}
