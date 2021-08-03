package com.fz.bookstoreapp.controllers;

import com.fz.bookstoreapp.entities.Book;
import com.fz.bookstoreapp.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@RestController
@Api(value = "BookControllerApi",produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "api/books")
    @ApiOperation("Gets all the books")
    public List<Book> getAll(){
        return bookService.getAllBooks();
    }

    @ApiOperation("Gets a book with the specific id")
    @GetMapping("api/books/{id}")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "OK",response = Book.class)})
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        try {
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/books/add")
    @ApiOperation("Adds a book to the database")
    public void createBook(@RequestBody Book book)
    {
        bookService.createBook(book);
    }
    @PutMapping("/api/books/{id}")
    @ApiOperation("Updates specific book with the selected id")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id) {
        try {
            Book oldBook = bookService.getBookById(id);
            oldBook.setId(book.getId());
            oldBook.setBookType(book.getBookType());
            oldBook.setBookTypeId(book.getBookTypeId());
            oldBook.setName(book.getName());
            oldBook.setPrice(book.getPrice());
            oldBook.setPublishDate(book.getPublishDate());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("api/books/{id}")
    @ApiOperation("Deletes specific book with the selected id")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }


}
