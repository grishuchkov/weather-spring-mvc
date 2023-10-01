package ru.grishuchkov.weather.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grishuchkov.weather.entity.User;
import ru.grishuchkov.weather.exception.DuplicateLoginException;
import ru.grishuchkov.weather.exception.UserNotFoundException;
import ru.grishuchkov.weather.repo.ifc.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByLogin(String login) {
        String SQL = "SELECT * FROM users WHERE login =?";

        return jdbcTemplate.query(SQL, new Object[]{login},
                        new BeanPropertyRowMapper<>(User.class)).stream()
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found, bro"));
    }

    @Override
    public void save(User user) {
        String SQL = "INSERT INTO users(login, password, role) VALUES (?,?,?)";

        try {
            jdbcTemplate.update(SQL, user.getLogin(), user.getPassword(), user.getRole());
        } catch (DuplicateKeyException e) {
            throw new DuplicateLoginException("A user with this login already exists");
        }
    }
}
