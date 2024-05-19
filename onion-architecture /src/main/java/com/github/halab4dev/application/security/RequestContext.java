package com.github.halab4dev.application.security;

import com.github.halab4dev.common.jwt.JwtData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 *
 * @author halab
 */
public class RequestContext {

    public static String getUserId() {
        return getJwtData().getUserId();
    }

    public static JwtData getJwtData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (JwtData) authentication.getPrincipal();
    }
}
