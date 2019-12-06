
/// This BooksApi is the controller for the POSTMAN API


package com.codingdojo.mvc.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;


@RestController
public class BooksApi {
	
	//injecting bookService
	
	private final BookService bookService;
	
// now lets create constructor from the source tab "generate constructor using fields"
	
	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	} 
	
	// adding methods
	
    @RequestMapping("/api/books")
    public List<Book> index() {  // this index() method will return bookService.allBooks() / will return all the books for me.
        return bookService.allBooks();  /// allBooks() will call the method from the BookService allBooks() method
    }
    
    @RequestMapping(value="/api/books", method=RequestMethod.POST)
    public Book create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);  // it will create a Book from the Book contructor(Book.java)
        return bookService.createBook(book);// then we call the bookService and createBook method passing the book which then falls to save method in the BookService.java
    }
	
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }  /// here we should gonna see the book or nothing


    @RequestMapping(value="api/books/{id}", method =RequestMethod.PUT)
    public Book update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
    	Book book = bookService.updateBook(id, title, desc, lang, numOfPages);
    	return book;
    }
    
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

}

// now we r gonna use POSTMAN to use post request  to see all methods and services are working or not
// from POSTAN make POST request then you will find all attribues creation in the sql database.
