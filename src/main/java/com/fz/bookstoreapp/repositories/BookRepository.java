package com.fz.bookstoreapp.repositories;

import com.fz.bookstoreapp.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
