package com.github.halab4dev.application.usecase;

import com.github.halab4dev.application.configuration.JwtConfiguration;
import com.github.halab4dev.application.dto.request.LoginRequest;
import com.github.halab4dev.application.dto.response.LoginResponse;
import com.github.halab4dev.domain.model.User;
import com.github.halab4dev.domain.service.TokenService;
import com.github.halab4dev.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/*
 *
 * @author halab
 */
@Component
@AllArgsConstructor
public class LoginUseCase {

    private final JwtConfiguration jwtConfiguration;

    private final UserService userService;
    private final TokenService tokenService;

    public LoginResponse execute(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = userService.login(username, password);
        String accessToken = tokenService.generateAccessToken(user,
            jwtConfiguration.getAccessTokenSecret(), jwtConfiguration.getAccessTokenTtlMillis());
        return new LoginResponse(user.getId(), accessToken);
    }
}
