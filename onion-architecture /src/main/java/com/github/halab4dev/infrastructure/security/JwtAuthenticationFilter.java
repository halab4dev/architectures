package com.github.halab4dev.infrastructure.security;

import com.github.halab4dev.application.configuration.JwtConfiguration;
import com.github.halab4dev.common.jwt.JwtData;
import com.github.halab4dev.common.jwt.JwtUtils;
import com.github.halab4dev.common.util.StringUtils;
import com.github.halab4dev.domain.constant.RequestHeader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/*
 *
 * @author halab
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    private final JwtConfiguration jwtConfiguration;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String accessToken = getAccessToken(request);
        if (!StringUtils.isEmpty(accessToken)) {
            JwtData jwtData = JwtUtils.parseAccessToken(accessToken, jwtConfiguration.getAccessTokenSecret());

            Collection<SimpleGrantedAuthority> permissions = getPermissions(jwtData);

            Authentication authentication =
                new UsernamePasswordAuthenticationToken(jwtData, "", permissions);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(RequestHeader.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            return authorization;
        }
        return authorization.replaceFirst(BEARER, "");
    }

    private Collection<SimpleGrantedAuthority> getPermissions(JwtData jwtData) {
        return List.of(new SimpleGrantedAuthority(jwtData.getRole().toString()));
    }
}
