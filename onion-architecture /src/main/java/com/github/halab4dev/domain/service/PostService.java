package com.github.halab4dev.domain.service;

import com.github.halab4dev.domain.model.Post;
import com.github.halab4dev.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*
 *
 * @author halab
 */
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createNewPost(Post post) {
        post = postRepository.insert(post);
        return post;
    }

    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }
}
