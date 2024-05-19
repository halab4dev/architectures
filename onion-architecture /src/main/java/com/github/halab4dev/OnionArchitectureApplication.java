package com.github.halab4dev;

import com.github.halab4dev.domain.constant.Role;
import com.github.halab4dev.infrastructure.repository.entity.UserEntity;
import com.github.halab4dev.infrastructure.repository.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 *
 * @author halab
 */
@AllArgsConstructor
@SpringBootApplication
public class OnionArchitectureApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnionArchitectureApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userJpaRepository.count() == 0) {
            UserEntity user = new UserEntity(
                "halab", passwordEncoder.encode("123456"), Role.USER
            );
            UserEntity admin = new UserEntity(
                "admin", passwordEncoder.encode("123456"), Role.ADMIN
            );
            userJpaRepository.save(user);
            userJpaRepository.save(admin);
        }
    }
}
