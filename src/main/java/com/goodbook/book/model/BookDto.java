package com.goodbook.book.model;

import com.goodbook.book.model.Enum.BookCategory;
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

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    BookCategory category;

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

    public List<CommentDto> _getComents() {
        return getComments();
    }

}
