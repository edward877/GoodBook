package com.goodbook.book.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 8516687341688452365L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name="order_generator", sequenceName = "order_seq")
    @Setter(AccessLevel.NONE)
    Long id;

    @Column(name = "sum", nullable = false)
    double sum;

    @Column(name = "status", nullable = false)
    String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", nullable = false)
    UserDto user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<OrderBookDto> orderBooks;

    public OrderDto(double sum, String status, UserDto  user) {
        this.sum = sum;
        this.status = status;
        this.user = user;
    }
}
