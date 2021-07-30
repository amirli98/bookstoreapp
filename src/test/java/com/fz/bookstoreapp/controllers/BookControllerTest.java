package com.fz.bookstoreapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fz.bookstoreapp.entities.BookType;
import com.fz.bookstoreapp.repositories.BookRepository;
import com.fz.bookstoreapp.services.BookService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fz.bookstoreapp.entities.Book;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author:Fazil Amirli
 * Created on: 7/30/2021
 */
@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
//@SpringBootTest
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookService bookService;
    private BookController bookController;
    @Test
    @SneakyThrows
    public void testList(){
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        BookType bookType1 = new BookType();
        bookType1.setId(1L);
        bookType1.setName("Drama");
        book1.setBookType(bookType1);
        book1.setBookTypeId(bookType1.getId());
        book1.setId(1L);
        book1.setPrice(12D);
        book1.setName("Pride and Prejudice");
        Book book2 = new Book();
        book2.setBookType(bookType1);
        book2.setBookTypeId(bookType1.getId());
        book2.setPrice(11.6D);
        book2.setName("Just Assasins");
        book2.setId(2L);
        books.add(book1);
        books.add(book2);
        bookType1.setBooks(books);
        Mockito.when(bookService.getAllBooks()).thenReturn(books);
        String url = "/books";
        MvcResult mvcResult= mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);
        String expectedJsonResponse = objectMapper.writeValueAsString(books);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }
}
