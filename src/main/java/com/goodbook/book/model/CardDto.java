package com.goodbook.book.model;

import lombok.Data;

import java.util.List;

@Data
public class CardDto {

    UserDto user;

    List<BookDto> books;
}
