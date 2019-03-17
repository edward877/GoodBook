package com.goodbook.book.model;

import lombok.*;
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

    @Column(name = "isPaid", nullable = false)
    boolean isPaid = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", nullable = false)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    UserDto user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    @EqualsAndHashCode.Exclude @ToString.Exclude
//    @Getter(AccessLevel.PRIVATE)
    List<OrderBookDto> orderBooks;

    public void plusSumToOrder(double sumForOneBook) {
        sum += sumForOneBook;
    }

    public OrderDto(UserDto user) {
        sum = 0;
        this.user = user;
    }

}
