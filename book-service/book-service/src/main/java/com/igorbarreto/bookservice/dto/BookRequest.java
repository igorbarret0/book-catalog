package com.igorbarreto.bookservice.dto;

import com.igorbarreto.bookservice.model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record BookRequest(
        String author,
        String launchDate,
        Double price,
        String title
) {

    public Book toModel() throws ParseException {

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date fmtDate = fmt.parse(launchDate);

        return new Book(author, fmtDate, price, title);
    }

}
