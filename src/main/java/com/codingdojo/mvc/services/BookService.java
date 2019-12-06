package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.repositories.BookRepository;

@Service
public class BookService {
	
// // adding the book repository as a dependency
	
   private final BookRepository bookRepository;   /// this variable bookRepository will call all the methods we have in the BookRepository for example find all
	
 /// now add this service dependency injection to have this service available to use
   
   public BookService(BookRepository bookRepository) {
	   this.bookRepository = bookRepository;	   
   }
   
 /// now we going to write methods to wrap our CRUD repository interface
   
   // returns all the books
   public List<Book> allBooks() {
       return bookRepository.findAll();
   }
   // creates a book
   public Book createBook(Book b) {
       return bookRepository.save(b);
   }
   // retrieves a book
   public Book findBook(Long id) {
       Optional<Book> optionalBook = bookRepository.findById(id);
       if(optionalBook.isPresent()) {
           return optionalBook.get();
       } else {
           return null;   /// /// here we should gonna see the book or nothing
       }
   }

   /// edits/update book
   
   public Book updateBook(Book b) {
		return bookRepository.save(b);
	}
   
   public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
	Optional <Book> editbook = bookRepository.findById(id);
	if (editbook != null) {
		editbook.get().setTitle(title);
		editbook.get().setDescription(desc);
		editbook.get().setLanguage(lang);
		editbook.get().setNumberOfPages(numOfPages);
		bookRepository.save(editbook.get());  // now this one saving the editbook
		return editbook.get();
	}
	return null;
}

	// deletes a book
	public void deleteBook(Long id) {
	bookRepository.deleteById(id);
}
  
   
}

/// now we created models and service together and its time to create controller. lets create a new package called controller. Inside of the package we will create new class.











