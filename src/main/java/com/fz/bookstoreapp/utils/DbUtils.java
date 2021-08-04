package com.fz.bookstoreapp.utils;

import com.fz.bookstoreapp.entities.Book;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Author:Fazil Amirli
 * Created on: 8/4/2021
 */
@UtilityClass
public class DbUtils {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/task";
    private static final String dbUsername = "root";
    private static final String dbPassword = "1234";

    public static @NotNull List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        Statement statement;
        try(Connection connection = DriverManager.getConnection(dbUrl,dbUsername , dbPassword)){
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from task.book");
            while (resultSet.next()){
                Book book = new Book();
                book.setPublishDate(resultSet.getDate("publishdate"));
                book.setBookTypeId(resultSet.getLong("booktype_id"));
                book.setPrice(resultSet.getDouble("price"));
                book.setName(resultSet.getString("name"));
                book.setId(resultSet.getLong("id"));
                books.add(book);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return books;
    }

    @Nullable
    public static Book getBookById(Long id){
        Statement statement;
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)){
            Book book = new Book();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select * from task.book where id=%d",id));
            while(resultSet.next()){
                book.setId(resultSet.getLong("id"));
                book.setPublishDate(resultSet.getDate("publishdate"));
                book.setBookTypeId(resultSet.getLong("booktype_id"));
                book.setPrice(resultSet.getDouble("price"));
                book.setName(resultSet.getString("name"));
            }
            return book;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
