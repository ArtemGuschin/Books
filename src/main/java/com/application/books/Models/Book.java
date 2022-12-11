package com.application.books.Models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    private int id;
    @NotEmpty(message = "Поле автор  должно  быть заполнено")
    private String author;
    @NotEmpty(message = "Поле название должно быть заполнено")
    private String nameOfBook;
    @NotEmpty(message = "Поле жанр должно быть заполнено")
    private String genre;


}

