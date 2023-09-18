package ru.grishuchkov.weather.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.model.UserRegistration;
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
        model.addAttribute("userRegistration", new UserRegistration());

        return "register";
    }

    @PostMapping("/register")
    public String registrationUser(@ModelAttribute("userRegistration") UserRegistration userRegistration) {

        userService.save(userRegistration);

        return "redirect:login";
    }
}
