package com.github.halab4dev.infrastructure.repository.entity;

import com.github.halab4dev.domain.constant.Role;
import com.github.halab4dev.domain.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *
 * @author halab
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity {

    public static final String TABLE_NAME = "`user`";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = USERNAME)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = ROLE)
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public UserEntity(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
