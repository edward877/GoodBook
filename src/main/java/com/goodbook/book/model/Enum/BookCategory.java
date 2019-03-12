package com.goodbook.book.model.Enum;

public enum BookCategory {
    DETECTIVE("Детектив"),
    NOVEL("Новела"),
    ADVENTURE("Приключения"),
    HORROR("Ужасы"),
    COMEDY("Комедия");

    String name;

    BookCategory(String name) {
        this.name = name;
    }

}
