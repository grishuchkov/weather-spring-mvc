package ru.grishuchkov.weather.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grishuchkov.weather.entity.User;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserByLogin(String login) {
        String SQL = "SELECT * FROM users WHERE login =?";
        return jdbcTemplate.query(SQL, new Object[]{login}, new BeanPropertyRowMapper<>(User.class)).stream().findFirst().orElse(null);
    }

    public void save(User user) {
        String SQL = "INSERT INTO users(login, password, role) VALUES (?,?,?)";
        jdbcTemplate.update(SQL, user.getLogin(), user.getPassword(), user.getRole());
    }
}
