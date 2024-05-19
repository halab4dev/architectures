package com.github.halab4dev.domain.repository;

import com.github.halab4dev.domain.model.Post;

/*
 *
 * @author halab
 */
public interface PostRepository {

    Post insert(Post post);

    void deleteById(String postId);
}
