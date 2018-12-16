package com.goodbook.book.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BookDto implements Serializable {

    private static final long serialVersionUID = 1038362193723415060L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "book_seq")
    Integer id;

    @Column(name = "name", nullable = false, length = 60)
    String name;

    @Column(name = "author", nullable = false, length = 60)
    String author;

    @Column(name = "year", nullable = false)
    int year;

    @Column(name = "description", nullable = false, length = 180)
    String description;

    @Column(name = "price", nullable = false)
    double price;

    @Column(name = "image", nullable = false, length = 120)
    String urlImage;

    @Column(name = "count", nullable = false)
    int count;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    List<CommentDto> comments = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    List<OrderBookDto> orderBooks = new ArrayList<>();

    public BookDto(String name, String author, int year, String description, double price, String urlImage, int count) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.count = count;
    }
}
