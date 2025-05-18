package com.book.website.Book_Management_App.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String BookName;

    @Column(length = 20, nullable = false)
    private String AuthorName;

    @Column(length = 15, nullable = false)
    private int PublishYear;

    @Column(length = 25, nullable = false)
    private int AvaStock;

    @Column(length = 30, nullable = false)
    private double Prize;
}

