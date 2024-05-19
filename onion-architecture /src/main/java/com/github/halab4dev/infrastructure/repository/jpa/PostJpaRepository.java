package com.github.halab4dev.infrastructure.repository.jpa;

import com.github.halab4dev.infrastructure.repository.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

/*
 *
 * @author halab
 */
public interface PostJpaRepository extends CrudRepository<PostEntity, String> {
}
