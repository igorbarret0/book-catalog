package com.igorbarreto.bookservice.controller;

import com.igorbarreto.bookservice.dto.BookRequest;
import com.igorbarreto.bookservice.dto.BookResponse;
import com.igorbarreto.bookservice.model.Book;
import com.igorbarreto.bookservice.proxy.CambioProxy;
import com.igorbarreto.bookservice.repository.BookRepository;
import com.igorbarreto.bookservice.response.Cambio;
import com.igorbarreto.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.HashMap;


@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/{id}/{currency}")
    public Book findBookById(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "currency") String currency) {

        return bookService.findBookById(id, currency);

    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(@RequestBody BookRequest request) throws ParseException {

       var savedBook = bookService.saveBook(request);

       return new ResponseEntity<>(savedBook, HttpStatus.CREATED);



    }

}
