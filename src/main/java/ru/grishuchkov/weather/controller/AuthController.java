package ru.grishuchkov.weather.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.grishuchkov.weather.dto.request.UserRegistrationDto;
import ru.grishuchkov.weather.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Validated
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping({"/", "/login"})
    public String getLoginPage(@AuthenticationPrincipal Principal principal) {

        if (principal != null) {
            return "redirect:/main";
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model, @AuthenticationPrincipal Principal principal) {

        if (principal != null) {
            return "redirect:/main";
        }
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());

        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute("userRegistrationDto") @Valid UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userRegistrationDto);
        return "redirect:login";
    }
}
