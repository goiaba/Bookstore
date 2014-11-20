/**
 *
 */
package edu.luc.fall2014.comp433.project.service.workflow.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import edu.luc.fall2014.comp433.project.dao.BookDao;
import edu.luc.fall2014.comp433.project.model.Book;
import edu.luc.fall2014.comp433.project.service.workflow.BookActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
@Named
public class BookActivityImpl extends BaseActivityImpl<Short, Book, BookDao>
		implements BookActivity {

	@Inject
	BookDao bookDAO;

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Book searchById(Short id) {
		if (null == id)
			throw new IllegalArgumentException("id must be passed.");
		return bookDAO.findById(id);
	}

	/**
	 * @param ids
	 * @param title
	 * @param author
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	@Override
	public List<Book> genericSearch(List<Short> ids, String title,
			String author, BigDecimal minPrice, BigDecimal maxPrice) {
		List<Book> books = new ArrayList<Book>();

		if (null != ids && !ids.isEmpty())
			books.addAll(bookDAO.findById(ids));
		else if (null != title)
			books.addAll(bookDAO.searchByTitle(title));
		else if (null != author)
			books.addAll(bookDAO.searchByAuthor(author));
		else
			books.addAll(bookDAO.searchByPrice(minPrice, maxPrice));
		return books;
	}

	@Override
	public List<Book> findBookByIds(List<Short> bookIdList) {
		return bookDAO.findById(bookIdList);
	}
}
