package com.book.website.Book_Management_App.service;

import com.book.website.Book_Management_App.model.BookDetails;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface BookServiceInterface {

 public List<BookDetails> getallBooks();
 public BookDetails savenewBook(BookDetails bookDetails);
 public BookDetails getnewBooks();
 public BookDetails getBook(int id) throws Exception;

 public void updatebooks(int id,BookDetails bookDetails) throws Exception;
 public void deleteBook(int id) throws Exception;
 public BookDetails getBookById(int id);
 public void generatePdf(HttpServletResponse response) throws IOException;

}
