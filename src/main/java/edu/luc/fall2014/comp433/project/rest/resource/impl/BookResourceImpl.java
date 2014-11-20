package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.fall2014.comp433.project.model.Book;
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

		if (null == id)
			throw new WebApplicationException(400);

		Book book = bookActivity.searchById(id);

		if (null == book)
			throw new WebApplicationException(404);

		return Response.status(Status.OK).entity(book).build();
	}

	@Override
	public Response retrieve(List<Short> ids, String title, String author, BigDecimal minPrice,
			BigDecimal maxPrice) {

		List<Book> books = bookActivity.genericSearch(ids, title, author,
				minPrice, maxPrice);

		if (books.isEmpty())
			throw new WebApplicationException(404);

		return Response.status(Status.OK).entity(books).build();
	}
}