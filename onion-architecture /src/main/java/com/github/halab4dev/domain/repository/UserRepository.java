package com.github.halab4dev.domain.repository;

import com.github.halab4dev.domain.model.User;

/*
 *
 * @author halab
 */
public interface UserRepository {

    User findById(String id);

    User findByUsername(String username);
}
