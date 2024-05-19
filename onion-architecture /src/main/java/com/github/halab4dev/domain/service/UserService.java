package com.github.halab4dev.domain.service;

import com.github.halab4dev.domain.model.User;
import com.github.halab4dev.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 *
 * @author halab
 */
@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Incorrect username or password");
        }

        return user;
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }
}
