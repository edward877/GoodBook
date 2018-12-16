package com.goodbook.book.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserDto implements Serializable {

    private static final long serialVersionUID = -7911677343698774614L;

    @NotBlank
    @Id
    @Column(name = "username", nullable = false, length = 40)
    String username;

    @NotBlank
    @Column(name = "password", nullable = false, length = 255)
    String password;

    @NotNull
    @Column(name = "role", nullable = false, length = 40)
    String role;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<CommentDto> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<OrderDto> orders;

    public UserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
