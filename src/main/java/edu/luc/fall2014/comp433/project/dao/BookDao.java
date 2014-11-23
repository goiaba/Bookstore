package edu.luc.fall2014.comp433.project.dao;

import java.math.BigDecimal;
import java.util.List;

import edu.luc.fall2014.comp433.project.model.Book;

public interface BookDao extends BaseDao<Short, Book> {

	Book searchByIsbn(String isbn);
	
	List<Book> searchByTitle(String title);

	List<Book> searchByAuthor(String author);

	List<Book> searchByPrice(BigDecimal minPrice, BigDecimal maxPrice);

	String findDescriptionByBookId(Short id);

}
