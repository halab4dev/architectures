package com.github.halab4dev.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 *
 * @author halab
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    private String userId;
    private String accessToken;
}
