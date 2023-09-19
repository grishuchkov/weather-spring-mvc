package ru.grishuchkov.weather.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.model.UserRegistrationDto;
import ru.grishuchkov.weather.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String reg(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());

        return "register";
    }

    @PostMapping("/register")
    public String registrationUser(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto) {

        userService.save(userRegistrationDto);

        return "redirect:login";
    }
}
