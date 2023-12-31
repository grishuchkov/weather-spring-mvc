package ru.grishuchkov.weather.exception.handler;

import lombok.SneakyThrows;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import ru.grishuchkov.weather.dto.request.UserRegistrationDto;
import ru.grishuchkov.weather.exception.DuplicateLocationException;
import ru.grishuchkov.weather.exception.DuplicateLoginException;
import ru.grishuchkov.weather.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object o,
                                         Exception ex) {

        if (ex instanceof DuplicateLoginException) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("error", ex.getMessage());
            mav.addObject("userRegistrationDto", new UserRegistrationDto());
            return mav;
        }

        if(ex instanceof DuplicateLocationException){
            ModelAndView mav = new ModelAndView("redirect:/main");
            return mav;
        }

        if (ex instanceof UserNotFoundException) {
            throw new BadCredentialsException(ex.getMessage());
        }

        return null;
    }
}

