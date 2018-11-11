package com.goodbook.book.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CommentDto implements Serializable {

    private static final long serialVersionUID = -7332466352744457697L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    @SequenceGenerator(name="comment_generator", sequenceName = "comment_seq")
    @Setter(AccessLevel.NONE)
    Long id;

    @Column(nullable = false)
    Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    UserDto user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    BookDto book;

    public CommentDto(Date date, UserDto user, BookDto book) {
        this.date = date;
        this.user = user;
        this.book = book;
    }
}
