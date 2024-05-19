package com.github.halab4dev.infrastructure.repository.entity;

import com.github.halab4dev.domain.model.Post;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
 *
 * @author halab
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = PostEntity.TABLE_NAME)
public class PostEntity {

    public static final String TABLE_NAME = "post";

    public static final String ID = "id";
    public static final String AUTHOR_ID = "author_id";
    public static final String CONTENT = "content";
    public static final String CREATED_TIME = "created_time";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JoinColumn(name = AUTHOR_ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;

    @Column(name = CONTENT)
    private String content;

    @Column(name = CREATED_TIME)
    private Date createdTime;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.createdTime = post.getCreatedTime();
    }
}
