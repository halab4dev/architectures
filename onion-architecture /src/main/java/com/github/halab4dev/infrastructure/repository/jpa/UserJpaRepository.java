package com.github.halab4dev.infrastructure.repository.jpa;

import com.github.halab4dev.infrastructure.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
 *
 * @author halab
 */
public interface UserJpaRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);
}
