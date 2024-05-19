package com.github.halab4dev.domain.model;

import lombok.Data;

import java.util.Date;

/*
 *
 * @author halab
 */
@Data
public class Post {

    private String id;
    private User author;
    private String content;
    private Date createdTime;

    public Post(User author, String content) {
        this.author = author;
        this.content = content;
        this.createdTime = new Date();
    }
}
