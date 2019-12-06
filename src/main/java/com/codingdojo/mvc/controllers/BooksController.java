package com.codingdojo.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

@Controller
public class BooksController {
    private final BookService bookService;
    
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @RequestMapping("/books")
    public String index(Model model) {  // this Model information for the template only
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "/books/index.jsp";
    }
    
    @RequestMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new.jsp";
    }
    
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/books/new.jsp";
        } else {
            bookService.createBook(book);
            return "redirect:/books";
        }
    }
    

    
    @RequestMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "/books/edit.jsp";
    }
    
    @RequestMapping(value="/books/{id}", method=RequestMethod.POST)
    public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    	if (result.hasErrors()) {
            return "/books/edit.jsp";
        } else {
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }
    @RequestMapping(value="/books/{id}/delete", method=RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
    
    @GetMapping("/books/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
    	Book book = bookService.findBook(id);
    	model.addAttribute("book", book);
    	return "/books/show.jsp";
    }
}


//Here we see a new use of @ModelAttribute, we are not accessing data sent with the request, but rather instantiating a new Book type and binding to our view model. Since there is no Book being passed, an empty book will be instantiated and bound to our view model. Why would we want this though? The binding to our model will allow us to create a form that is also bound to the Book type, which will allow us to validate the information against our Book type!