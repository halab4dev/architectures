package com.github.halab4dev.application.usecase;

import com.github.halab4dev.application.dto.request.CreateNewPostRequest;
import com.github.halab4dev.application.dto.response.CreateNewPostResponse;
import com.github.halab4dev.application.security.RequestContext;
import com.github.halab4dev.domain.model.Post;
import com.github.halab4dev.domain.model.User;
import com.github.halab4dev.domain.service.PostService;
import com.github.halab4dev.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/*
 *
 * @author halab
 */
@Component
@AllArgsConstructor
public class CreateNewPostUseCase {

    private final PostService postService;
    private final UserService userService;

    @PreAuthorize("hasAuthority('USER')")
    public CreateNewPostResponse execute(CreateNewPostRequest request) {
        String content = request.getContent();
        String authorId = RequestContext.getUserId();

        User author = userService.findById(authorId);

        Post post = new Post(author, content);
        post = postService.createNewPost(post);

        return new CreateNewPostResponse(post.getId());
    }
}
