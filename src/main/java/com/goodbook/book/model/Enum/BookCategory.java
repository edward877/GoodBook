package com.goodbook.book.model.Enum;

public enum BookCategory {
    DETECTIVE("Детектив"),
    NOVEL("Новела"),
    ADVENTURE("Приключения"),
    HORROR("Ужасы"),
    EDUCATION("Образование"),
    COMEDY("Комедия");

    String name;

    BookCategory(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return name;
    }
}
