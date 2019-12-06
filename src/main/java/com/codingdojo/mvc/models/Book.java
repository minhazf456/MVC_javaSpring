package com.codingdojo.mvc.models;

import java.util.Date;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")   // these two annotations telling our database that we gonna have this table

// now we can create our fields

	public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, max = 200)
    private String title;
    @Size(min = 5, max = 200)
    private String description;
    @Size(min = 3, max = 40)
    private String language;
    @Min(100)
    private Integer numberOfPages;
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
	

//// now lets add our constructor
    
    public Book() {	
    	
  	}
    
    
public Book(String title,  String description, String language, Integer numberOfPages) {
	
	this.title = title;
	this.description = description;
	this.language = language;
	this.numberOfPages = numberOfPages;
}


// lets add getters and setters


////Getters

		public Long getId() {
		return id;
		}
		public String getTitle() {
		return title;
		}
		public String getDescription() {
		return description;
		}
		public String getLanguage() {
		return language;
		}
		public Integer getNumberOfPages() {
		return numberOfPages;
		}
		public Date getCreatedAt() {
		return createdAt;
		}
		public Date getUpdatedAt() {
		return updatedAt;
		}

///setters

		public void setId(Long id) {
			this.id = id;
		}		
		public void setTitle(String title) {
			this.title = title;
		}		
		public void setDescription(String description) {
			this.description = description;
		}		
		public void setLanguage(String language) {
			this.language = language;
		}
		
		public void setNumberOfPages(Integer numberOfPages) {
			this.numberOfPages = numberOfPages;
		}
		
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
       

	
	/// right before the object is created and save the date the object is created at and object is being updated the database table.
	




	@PrePersist
	protected void onCreate(){
    this.createdAt = new Date();
}
	@PreUpdate
	protected void onUpdate(){
    this.updatedAt = new Date();
}
	

	

}
