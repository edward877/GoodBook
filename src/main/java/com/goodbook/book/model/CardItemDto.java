package com.goodbook.book.model;

import lombok.Data;

@Data
public class CardItemDto {

    String author;

    String name;

    double price;

    int id;

    String urlImage;

    int countInCard;
}
