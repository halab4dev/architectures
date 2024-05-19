package com.github.halab4dev.application.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/*
 *
 * @author halab
 */
@Getter
@Configuration
public class JwtConfiguration {

    @Value("${application.security.jwt.accessToken.secret}")
    private String accessTokenSecret;

    @Value("${application.security.jwt.accessToken.ttlMillis}")
    private Long accessTokenTtlMillis;
}
