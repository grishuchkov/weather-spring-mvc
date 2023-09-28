package ru.grishuchkov.weather.dto.request;


import javax.validation.constraints.Size;

public class UserRegistrationDto {
    @Size(min = 5, max = 25, message = "The login must be from 5 to 25 characters")
    private String login;
    @Size(min = 5, max = 25, message = "The password must be from 5 to 25 characters")
    private String password;

    public UserRegistrationDto() {
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
