package com.github.halab4dev.infrastructure.repository.adapter;

import com.github.halab4dev.common.util.ModelMapperUtils;
import com.github.halab4dev.domain.model.User;
import com.github.halab4dev.domain.repository.UserRepository;
import com.github.halab4dev.infrastructure.repository.entity.UserEntity;
import com.github.halab4dev.infrastructure.repository.jpa.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/*
 *
 * @author halab
 */
@Component
@AllArgsConstructor
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findById(String id) {
        UserEntity userEntity = userJpaRepository.findById(id).orElse(null);
        return ModelMapperUtils.toObject(userEntity, User.class);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = userJpaRepository.findByUsername(username).orElse(null);
        return ModelMapperUtils.toObject(userEntity, User.class);
    }
}
