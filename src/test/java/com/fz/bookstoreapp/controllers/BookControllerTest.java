package com.fz.bookstoreapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fz.bookstoreapp.entities.Book;
import com.fz.bookstoreapp.entities.BookType;
import com.fz.bookstoreapp.repositories.BookRepository;
import com.fz.bookstoreapp.services.BookService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    @SneakyThrows
    @Test
    public void testAddBook(){
        BookType bookType = new BookType(1L,"Drama",null,null);
        Book book1 = new Book(1L,"Pride and Prejudice",null,12D,1L,bookType);
        Book book2 = new Book(2L,"Just Assasins",null,11.6D,1L,bookType);
        List<Book> books = new ArrayList<>(List.of(new Book[]{book1, book2}));
        bookType.setBooks(books);
        Mockito.when(bookService.createBook(book1)).thenReturn(book1);
        String url = "/api/books/add";
        mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book1)).with(csrf()))
                .andExpect(status().isOk()).andExpect(content().string(""));
    }
}
