package com.github.halab4dev.domain.model;

import com.github.halab4dev.domain.constant.Role;
import lombok.Data;

/*
 *
 * @author halab
 */
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private Role role;
}
