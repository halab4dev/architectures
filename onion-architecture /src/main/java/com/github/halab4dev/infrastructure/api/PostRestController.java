package com.github.halab4dev.infrastructure.api;

import com.github.halab4dev.application.dto.request.CreateNewPostRequest;
import com.github.halab4dev.application.dto.response.CreateNewPostResponse;
import com.github.halab4dev.application.usecase.CreateNewPostUseCase;
import com.github.halab4dev.application.usecase.DeletePostUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
 *
 * @author halab
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1/posts")
public class PostRestController {

    private final CreateNewPostUseCase createNewPostUseCase;
    private final DeletePostUseCase deletePostUseCase;

    @PostMapping
    public CreateNewPostResponse createNewPost(@RequestBody CreateNewPostRequest request) {
        return createNewPostUseCase.execute(request);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") String postId) {
        deletePostUseCase.execute(postId);
    }
}
