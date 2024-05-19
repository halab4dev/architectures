package com.github.halab4dev.infrastructure.api;

import com.github.halab4dev.application.dto.request.LoginRequest;
import com.github.halab4dev.application.dto.response.LoginResponse;
import com.github.halab4dev.application.usecase.LoginUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * @author halab
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1/access_tokens")
public class AccessTokenRestController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginUseCase.execute(request);
    }
}
