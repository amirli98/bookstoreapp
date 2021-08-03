package com.fz.bookstoreapp.services;

import com.fz.bookstoreapp.entities.Book;
import com.fz.bookstoreapp.repositories.BookRepository;
import com.fz.bookstoreapp.repositories.BookTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return new ArrayList<>(bookRepository.findAll());
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).get();
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Book newBook, Long bookId){
        Book book = new Book();
        book.setBookType(newBook.getBookType());
        book.setId(bookId);
        book.setName(newBook.getName());
        book.setPrice(newBook.getPrice());
        book.setPublishDate(newBook.getPublishDate());
        return bookRepository.save(book);
    }

}
