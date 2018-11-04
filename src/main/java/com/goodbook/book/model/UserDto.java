package com.goodbook.book.model;

import com.goodbook.book.security.Enum.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserDto implements Serializable {

    private static final long serialVersionUID = -7911677343698774614L;

    @Id
    @Column(name = "username", nullable = false, length = 40)
    String username;

    @Column(name = "fio", nullable = false, length = 40)
    String fio;

    @Column(name = "password", nullable = false, length = 255)
    String password;

    @Column(name = "role", nullable = false, length = 40)
    UserRole role;
}
