package com.book.website.Book_Management_App.repository;

import com.book.website.Book_Management_App.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDetails,Integer> {
}
