package com.github.halab4dev.common.jwt;

import com.github.halab4dev.domain.constant.Role;
import lombok.Builder;
import lombok.Data;

/*
 *
 * @author halab
 */
@Data
@Builder
public class JwtData {

    public static final String USER_ID = "user_id";
    public static final String ROLE = "role";

    private String userId;
    private Role role;
}
