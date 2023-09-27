package ru.grishuchkov.weather.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grishuchkov.weather.entity.Location;
import ru.grishuchkov.weather.entity.User;
import ru.grishuchkov.weather.repo.ifc.LocationRepository;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveNewLocationByUser(Location location, User user) {
        String SQL = "INSERT INTO locations(user_login, name, country, state, latitude, longitude) VALUES (?,?,?,?,?,?)";

        jdbcTemplate.update(SQL,
                user.getLogin(),
                location.getName(),
                location.getCountry(),
                location.getState(),
                location.getLat(),
                location.getLon());
    }
}
