package com.fz.bookstoreapp.services;

import com.fz.bookstoreapp.entities.BookType;
import com.fz.bookstoreapp.repositories.BookTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@Service
public class BookTypeService {
    @Autowired
    private BookTypeRepository bookTypeRepository;

    public List<BookType> getBookTypesForDropDown(){
        return bookTypeRepository.findAll();
    }
}
