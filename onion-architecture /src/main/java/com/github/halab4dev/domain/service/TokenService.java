package com.github.halab4dev.domain.service;

import com.github.halab4dev.common.jwt.JwtData;
import com.github.halab4dev.common.jwt.JwtUtils;
import com.github.halab4dev.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
 *
 * @author halab
 */
@Service
public class TokenService {

    public String generateAccessToken(User user, String secret, long ttl) {
        JwtData jwtData = JwtData.builder()
            .userId(user.getId())
            .role(user.getRole())
            .build();
        return JwtUtils.generateJwt(jwtData, secret, new Date(System.currentTimeMillis() + ttl));
    }
}
