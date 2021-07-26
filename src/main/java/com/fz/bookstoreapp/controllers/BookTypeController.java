package com.fz.bookstoreapp.controllers;

import com.fz.bookstoreapp.entities.Book;
import com.fz.bookstoreapp.entities.BookType;
import com.fz.bookstoreapp.services.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@RestController
public class BookTypeController {
    @Autowired
    private BookTypeService bookTypeService;
    @GetMapping(value = "/booktypes")
    public List<BookType> list(){
        return bookTypeService.getBookTypesForDropDown();
    }
}
