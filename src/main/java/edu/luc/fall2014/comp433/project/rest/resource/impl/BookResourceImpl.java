package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Book;
import edu.luc.fall2014.comp433.project.rest.representation.BookDescriptionRepresentation;
import edu.luc.fall2014.comp433.project.rest.representation.BookRepresentation;
import edu.luc.fall2014.comp433.project.rest.representation.BookstoreURI;
import edu.luc.fall2014.comp433.project.rest.resource.BookService;
import edu.luc.fall2014.comp433.project.service.workflow.BookActivity;

/**
 * 
 * @author Thiago Vieira Puluceno
 * 
 */

@RequestScoped
public class BookResourceImpl extends BaseResourceImpl<Short, Book> implements
		BookService {

	@Inject
	private BookActivity bookActivity;

	@Override
	public Response retrieve(Short id) {
		Response response = notFound();
		Book book = null;
		if (null != id) {
			book = bookActivity.searchById(id);
			if (null != book) {
				// response = ok(book);
				// TODO enable book representation
				BookRepresentation bookRep = new BookRepresentation(book,
						new BookstoreURI(getRequestUri()));
				response = ok(bookRep);
			}
		}
		return response;
	}

	@Override
	public Response retrieve(List<Short> ids, String title, String author,
			BigDecimal minPrice, BigDecimal maxPrice) {
		Response response = notFound();
		List<Book> books = bookActivity.genericSearch(ids, title, author,
				minPrice, maxPrice);
		if (!books.isEmpty()) {
			// response = ok(books);
			// TODO enable book representation
			List<BookRepresentation> bookRepList = BookRepresentation
					.fromModelList(BookRepresentation.class, Book.class, books,
							new BookstoreURI(getRequestUri()));
			response = ok(bookRepList);
		}
		return response;
	}

	@Override
	public Response getBookDescription(Short id) {
		Response response = notFound();
		String description = null;
		if (null != id) {
			description = bookActivity.retrieveBookDescription(id);
			if (null != description) {
				BookDescriptionRepresentation desRep = new BookDescriptionRepresentation(
						id, description, new BookstoreURI(getRequestUri()));
				response = ok(desRep);
			}
		}
		return response;
	}
}