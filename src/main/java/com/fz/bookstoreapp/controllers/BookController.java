package com.fz.bookstoreapp.controllers;

import com.fz.bookstoreapp.entities.Book;
import com.fz.bookstoreapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public List<Book> list(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id){
        try {
            Book book = bookService.getBookById(id);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/books")
    public void addProduct(@RequestBody Book book)
    {
        bookService.createBook(book);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Book book, @PathVariable Long id) {
        try {
            Book oldProduct = bookService.getBookById(id);
            bookService.createBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        bookService.deleteBook(id);
    }


}
