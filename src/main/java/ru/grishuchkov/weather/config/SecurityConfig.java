package ru.grishuchkov.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import ru.grishuchkov.weather.config.filter.EncodingFilter;
import ru.grishuchkov.weather.exception.handler.AuthExceptionHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class)
            .exceptionHandling()
                .accessDeniedHandler(((request, response, exception) ->
                        response.sendRedirect("/")
                ))
        .and()
            .authorizeRequests()
            .antMatchers("/register/**", "/login").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .failureHandler(new AuthExceptionHandler())
            .usernameParameter("login")
            .loginPage("/login")
            .defaultSuccessUrl("/main")
            .successForwardUrl("/main")
        .and()
            .logout()
            .logoutSuccessUrl("/login")
            .and()
            .httpBasic()
                .disable();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }
}
