package com.fz.bookstoreapp.repositories;

import com.fz.bookstoreapp.entities.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {

}
