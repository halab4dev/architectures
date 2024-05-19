package com.github.halab4dev.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 *
 * @author halab
 */
@Component
public class UnauthorizedHandler extends SecurityExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request, HttpServletResponse response, AuthenticationException ex
    ) throws IOException {
        sendErrorResponse(response, "Unauthorized");
    }
}
