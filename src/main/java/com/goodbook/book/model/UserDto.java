package com.goodbook.book.model;

import com.goodbook.book.model.Enum.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
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
    @Column(name = "email", nullable = false, length = 40)
    @Email
    String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    @Getter(AccessLevel.PRIVATE)
    String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 40)
    UserRole role;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter(AccessLevel.PRIVATE)
    List<CommentDto> comments = new ArrayList<>();

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<OrderDto> orders;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    transient CardDto card = new CardDto();

    public String _getPassword() {
        return password;
    }
}
