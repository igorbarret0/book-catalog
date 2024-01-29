package com.igorbarreto.bookservice.dto;

public record BookResponse(
        String author,
        Double price,
        String title
) {
}
