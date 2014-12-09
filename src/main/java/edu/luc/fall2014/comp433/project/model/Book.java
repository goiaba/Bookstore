/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.luc.fall2014.comp433.project.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 
 * @author Thiago Vieira Puluceno
 */
@Entity
@Table(schema = "bookstore", uniqueConstraints = @UniqueConstraint(name = "BOOK_UN_CONSTRAINT_ISBN", columnNames = {"isbn"}))
@NamedQueries({
		@NamedQuery(name = Book.LIST_ALL_BOOKS, query = "SELECT b FROM Book b"),
		@NamedQuery(name = Book.FIND_BY_ID, query = "SELECT b FROM Book b WHERE b.id = :id"),
		@NamedQuery(name = Book.FIND_BY_IDS, query = "SELECT b FROM Book b WHERE b.id in (:idsList)"),
		@NamedQuery(name = Book.FIND_BY_ISBN, query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
		@NamedQuery(name = Book.FIND_BY_TITLE, query = "SELECT b FROM Book b WHERE LOWER(b.title) like :title"),
		@NamedQuery(name = Book.FIND_BY_AUTHOR, query = "SELECT b FROM Book b WHERE LOWER(b.author) like :author"),
		@NamedQuery(name = Book.FIND_BY_PRICE, query = "SELECT b FROM Book b WHERE b.price >= :minPrice and b.price <= :maxPrice") })
public class Book extends BaseEntity<Short> {

	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Book.findById";
	public static final String FIND_BY_IDS = "Book.findByIds";
	public static final String FIND_BY_ISBN = "Book.findByIsbn";
	public static final String FIND_BY_AUTHOR = "Book.findByAuthor";
	public static final String FIND_BY_TITLE = "Book.findByTitle";
	public static final String FIND_BY_PRICE = "Book.findByPrice";
	public static final String LIST_ALL_BOOKS = "LIST_ALL_BOOKS";

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Basic(optional = false)
	private String isbn;

	@Basic(optional = false)
	private String title;

	@Basic(optional = false)
	private String author;
	
	@Basic(optional = true)
	private String description;

	@Min(value = 0)
	@Max(value = 9999)
	@Basic(optional = false)
	private BigDecimal price;

	public Book() {
	}

	public Book(Short id) {
		this.id = id;
	}

	public Book(Short id, String title, String author, BigDecimal price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Short id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "model.Book[ id=" + id + " ]";
	}

}
