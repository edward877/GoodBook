package com.goodbook.book.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    String commentBody;

    @Column(nullable = false)
    LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(AccessLevel.PRIVATE)
    @JoinColumn(name = "user_id", nullable = false)
    UserDto user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter(AccessLevel.PRIVATE)
    @JoinColumn(name = "book_id", nullable = false)
    BookDto book;

    public String getUser() {
        return user.getEmail();
    }

}
