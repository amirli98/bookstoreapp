package com.fz.bookstoreapp.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Author:Fazil Amirli
 * Created on: 7/26/2021
 */
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private  String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "publishdate")
    private  Date publishDate = null;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "booktype_id",nullable = false,insertable = false,updatable = false)
    private Long bookTypeId;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booktype_id", referencedColumnName = "id")
    private BookType bookType;

}
