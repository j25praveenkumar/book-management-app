package com.book.website.Book_Management_App.controller;

import com.book.website.Book_Management_App.model.BookDetails;
import com.book.website.Book_Management_App.service.BookService;
import com.book.website.Book_Management_App.service.BookServiceInterface;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookMainController {

    @Autowired
    private  BookServiceInterface bookServiceInterface;

    // api 1
    @GetMapping("/allbooks")
    public ModelAndView getbookData()
    {
    return new ModelAndView("avalilable-books","books",bookServiceInterface.getallBooks());
    }
    //api 2
    @GetMapping("/savebook")
    public ModelAndView addtheBook(){
    return new ModelAndView("create-newbooks","book",bookServiceInterface.getnewBooks());
    }
    // api 3 { it belongs to api 2}
    @PostMapping("/new")
    public String newBook(@ModelAttribute("Book") BookDetails book)
    {
        bookServiceInterface.savenewBook(book);
        return "redirect:/allbooks";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBook(@PathVariable("id") int id)throws Exception {

        return new ModelAndView("update-books","book",bookServiceInterface.getBook(id) );
    }
    //apt 4 { update }
    @PostMapping("/update/{id}")
    public String updateDetails(@PathVariable("id") int id, @ModelAttribute("book") BookDetails book) throws Exception
    {
        bookServiceInterface.updatebooks(id, book);
        return "redirect:/allbooks";
    }

    @GetMapping("/drop/{id}")
    public ModelAndView dropDetails(@PathVariable("id") int id) throws Exception{
        return  new ModelAndView("delete-book","book",bookServiceInterface.getBook(id));
    }
    @PostMapping("/deleteBooks/{id}")
    public String deleteBooks(@PathVariable("id")int id) throws Exception{
    bookServiceInterface.deleteBook(id);
    return "redirect:/allbooks";
    }

    @GetMapping("/download/pdf")
    public void downloadPdf(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=books.pdf");

        bookServiceInterface.generatePdf(response);
    }

}
