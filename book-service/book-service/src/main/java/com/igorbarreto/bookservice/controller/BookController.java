package com.igorbarreto.bookservice.controller;

import com.igorbarreto.bookservice.dto.BookRequest;
import com.igorbarreto.bookservice.dto.BookResponse;
import com.igorbarreto.bookservice.model.Book;
import com.igorbarreto.bookservice.proxy.CambioProxy;
import com.igorbarreto.bookservice.repository.BookRepository;
import com.igorbarreto.bookservice.response.Cambio;
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
    private Environment environment;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = true)
    private CambioProxy cambioProxy;

    @GetMapping("/{id}/{currency}")
    public Book findBookById(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "currency") String currency) {

        var optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        var book = optionalBook.get();

        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());
        book.setCurrency(currency);

        return book;
    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(@RequestBody BookRequest request) throws ParseException {

        Book book = request.toModel();

        var bookSaved = bookRepository.save(book);

        var response = new BookResponse(
                bookSaved.getAuthor(),
                bookSaved.getPrice(),
                bookSaved.getTitle()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    /*
    @GetMapping("/{id}/{currency}")
    public Book findBookById(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "currency") String currency) {

        var optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        var book = optionalBook.get();

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var url = "http://localhost:8000/cambio-service/{amount}/{from}/{to}";

        var response = restTemplate.getForEntity(
                url,
                Cambio.class,
                params);

        var cambio = response.getBody();

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());
        book.setCurrency(currency);

        return book;
    }
    */
}
