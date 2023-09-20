package ru.grishuchkov.weather.exception.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthExceptionHandler implements AuthenticationFailureHandler {

    private final String ERROR_LOGIN_URL = "/login?error=";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {

        if (ex instanceof InternalAuthenticationServiceException) {
            sendRedirectWithMessage(response, ex.getMessage());
        }

        if (ex instanceof BadCredentialsException) {
            sendRedirectWithMessage(response, "login.error.invalid_credentials");
        }
    }

    private void sendRedirectWithMessage(HttpServletResponse response, String message) throws IOException {
        response.sendRedirect(ERROR_LOGIN_URL + message);
    }
}
