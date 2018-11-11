package com.goodbook.book.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order_books")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderBookDto  implements Serializable {

    private static final long serialVersionUID = 5331140908825226628L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_book_generator")
    @SequenceGenerator(name="order_book_generator", sequenceName = "order_book_seq")
    @Setter(AccessLevel.NONE)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    BookDto book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    OrderDto order;

    public OrderBookDto(BookDto book, OrderDto order) {
        this.book = book;
        this.order = order;
    }
}
