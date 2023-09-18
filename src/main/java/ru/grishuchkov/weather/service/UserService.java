package ru.grishuchkov.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.grishuchkov.weather.entity.User;
import ru.grishuchkov.weather.model.UserRegistration;
import ru.grishuchkov.weather.repo.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(username);

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getUserAuthorities());
    }

    public void save(UserRegistration userRegistration) {
        String encodePassword = getEncodePassword(userRegistration.getPassword());

        User user = new User(userRegistration.getLogin(), encodePassword, "USER");
        userRepository.save(user);
    }

    private List<SimpleGrantedAuthority> getUserAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER_ROLE"));
    }

    private String getEncodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
