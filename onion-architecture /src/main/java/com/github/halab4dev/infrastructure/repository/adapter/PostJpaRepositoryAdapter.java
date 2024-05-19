package com.github.halab4dev.infrastructure.repository.adapter;

import com.github.halab4dev.domain.model.Post;
import com.github.halab4dev.domain.repository.PostRepository;
import com.github.halab4dev.infrastructure.repository.entity.PostEntity;
import com.github.halab4dev.infrastructure.repository.jpa.PostJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/*
 *
 * @author halab
 */
@Component
@AllArgsConstructor
public class PostJpaRepositoryAdapter implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post insert(Post post) {
        PostEntity postEntity = new PostEntity(post);
        postJpaRepository.save(postEntity);
        post.setId(postEntity.getId());
        return post;
    }

    @Override
    public void deleteById(String postId) {
        postJpaRepository.deleteById(postId);
    }
}
