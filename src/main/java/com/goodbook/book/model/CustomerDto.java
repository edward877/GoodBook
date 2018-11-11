package com.goodbook.book.model;

import com.sun.istack.internal.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(exclude = {"user", "orders"})
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 197529698909317472L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name="customer_generator", sequenceName = "customer_seq")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    @Column(name = "fio", nullable = false, length = 40)
    String fio;

    @NotBlank
    @Column(name = "email", nullable = false, length = 40)
    @Email
    String email;

    @NotBlank
    @Column(name = "phoneNumber", nullable = false, length = 40)
    String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    UserDto user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<OrderDto> orders;

    public CustomerDto(String fio, String email, String phoneNumber, UserDto user) {
        this.fio = fio;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
