package com.github.halab4dev.infrastructure.security;

import com.github.halab4dev.common.constant.ContentType;
import com.github.halab4dev.common.dto.response.ErrorResponse;
import com.github.halab4dev.common.util.JsonUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/*
 *
 * @author halab
 */
@Component
public class SecurityExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            sendErrorResponse(response, ex.getMessage());
        }
    }

    void sendErrorResponse(
        HttpServletResponse httpServletResponse, String message
    ) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,DELETE,PUT,OPTIONS");
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON_UTF8.getValue());
        ErrorResponse errorResponse = new ErrorResponse(message);

        try (Writer writer = httpServletResponse.getWriter()) {
            writer.write(JsonUtils.toJson(errorResponse));
            writer.flush();
        }
    }
}
