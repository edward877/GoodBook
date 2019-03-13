package com.goodbook.book.model;

import lombok.*;
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

    @Column(nullable = false)
    int countBook;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    BookDto book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    OrderDto order;

    public OrderBookDto(BookDto book, OrderDto order, int countBook) {
        this.book = book;
        this.order = order;
        this.countBook = countBook;
    }
}
