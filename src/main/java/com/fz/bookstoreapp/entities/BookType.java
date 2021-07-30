package com.fz.bookstoreapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@Entity(name = "booktype")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private  Long id;


    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdtime")
    private Date createdTime = null;

    @OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "booktype_id",referencedColumnName = "id")
    @ToString.Exclude
    @JsonIgnore
    private List<Book> books;


}
