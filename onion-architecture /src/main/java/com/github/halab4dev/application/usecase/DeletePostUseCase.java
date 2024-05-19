package com.github.halab4dev.application.usecase;

import com.github.halab4dev.domain.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/*
 *
 * @author halab
 */
@Component
@AllArgsConstructor
public class DeletePostUseCase {

    private final PostService postService;

    @PreAuthorize("hasAuthority('ADMIN')")
    public void execute(String postId) {
        postService.deletePost(postId);
    }
}
