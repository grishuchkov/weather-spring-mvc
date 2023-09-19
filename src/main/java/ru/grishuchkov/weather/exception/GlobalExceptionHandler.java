package ru.grishuchkov.weather.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import ru.grishuchkov.weather.model.UserRegistrationDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception ex) {

        if (ex instanceof DuplicateLoginException) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("error", ex.getMessage());
            mav.addObject("userRegistrationDto", new UserRegistrationDto());
            return mav;
        }


        return null;
    }
}

