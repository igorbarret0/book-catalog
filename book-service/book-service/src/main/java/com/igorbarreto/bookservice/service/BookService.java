package com.igorbarreto.bookservice.service;

import com.igorbarreto.bookservice.dto.BookRequest;
import com.igorbarreto.bookservice.dto.BookResponse;
import com.igorbarreto.bookservice.model.Book;
import com.igorbarreto.bookservice.proxy.CambioProxy;
import com.igorbarreto.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Environment environment;

    @Autowired(required = true)
    private CambioProxy cambioProxy;

    public Book findBookById(Long id, String currency) {

        var optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        var book = optionalBook.get();

        var cambio = cambioProxy.getCambio(book.getPrice(), currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setPrice(cambio.getConvertedValue());
        book.setCurrency(currency);

        return book;

    }

    public BookResponse saveBook(BookRequest request) throws ParseException {


        Book book = request.toModel();

        var bookSaved = bookRepository.save(book);

        var response = new BookResponse(

                bookSaved.getAuthor(),
                bookSaved.getPrice(),
                bookSaved.getTitle()
        );

        return response;

    }

}
