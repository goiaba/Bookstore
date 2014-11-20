package edu.luc.fall2014.comp433.project.dao.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;

import edu.luc.fall2014.comp433.project.dao.BookDao;
import edu.luc.fall2014.comp433.project.model.Book;

/**
 *
 * @author Thiago Vieira Puluceno
 *
 */
@RequestScoped
public class BookDaoImpl extends BaseDaoImpl<Short, Book> implements BookDao {

	public BookDaoImpl() {
		super(Book.class);
	}
	
	@Override
	public Book searchByIsbn(String isbn) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_ISBN, Book.class)
					.setParameter("isbn", isbn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Book> searchByTitle(String title) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_TITLE, Book.class)
					.setParameter("title", "%" + title.toLowerCase() + "%").getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Book> searchByAuthor(String author) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_AUTHOR, Book.class)
					.setParameter("author", "%" + author.toLowerCase() + "%").getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Book> searchByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
		try {
			return super.getEntityManager()
					.createNamedQuery(Book.FIND_BY_PRICE, Book.class)
					.setParameter("minPrice", minPrice)
					.setParameter("maxPrice", maxPrice).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

}
