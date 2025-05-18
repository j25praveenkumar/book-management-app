package com.book.website.Book_Management_App.service;

import com.book.website.Book_Management_App.model.BookDetails;
import com.book.website.Book_Management_App.repository.BookRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Paragraph;

import java.io.IOException;
import java.util.List;

@Service
public class BookService implements BookServiceInterface{

    @Autowired
private BookRepository bookRepository;

    // m1
    @Override
    public List<BookDetails> getallBooks() {
        return bookRepository.findAll();
    }

    //m2
    @Override
    public BookDetails savenewBook(BookDetails bookDetails) {
        return bookRepository.save(bookDetails);
    }
    //m3
    @Override
    public BookDetails getnewBooks() {
        return new BookDetails();
    }
    //
    @Override
    public BookDetails getBook(int id) throws Exception {
        BookDetails bookDetails = new BookDetails();

        bookDetails= bookRepository.findById(id).orElse(null);

        if(bookDetails == null)
        {
            throw new Exception("can't find the data");
        }
        return bookDetails;
    }

    @Override
    public void updatebooks(int id, BookDetails bookDetails) throws Exception {

        BookDetails existingBook = getBookById(id);
        existingBook.setBookName(bookDetails.getBookName());
        existingBook.setAuthorName(bookDetails.getAuthorName());
        existingBook.setPublishYear(bookDetails.getPublishYear());
        existingBook.setAvaStock(bookDetails.getAvaStock());
        existingBook.setPrize(bookDetails.getPrize());

        bookRepository.save(existingBook);

    }

    @Override
    public void deleteBook(int id) throws Exception {

        bookRepository.deleteById(id);
    }
    @Override
    public BookDetails getBookById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public void generatePdf(HttpServletResponse response) throws IOException {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            List<BookDetails> books = getallBooks();

            books.forEach(book -> {
                try {
                    String data = "ID: " + book.getId() +
                            "\nBook Name: " + book.getBookName() +
                            "\nAuthor Name: " + book.getAuthorName() +
                            "\nPublish Year: " + book.getPublishYear() +
                            "\nAvailable Stock: " + book.getAvaStock() +
                            "\nPrice: ₹" + book.getPrize() +
                            "\n-------------------------------\n";
                    document.add(new Paragraph(data));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
