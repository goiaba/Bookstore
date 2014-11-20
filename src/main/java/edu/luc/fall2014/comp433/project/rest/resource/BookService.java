package edu.luc.fall2014.comp433.project.rest.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Book;

/**
 * 
 * @author Thiago Vieira Puluceno
 * 
 */
@Path("/books")
public interface BookService extends BaseService<Short, Book> {

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Response retrieve(@PathParam("id") Short id);

	@GET
	@Produces({ "application/json" })
	public Response retrieve(@QueryParam("id") List<Short> ids,
			@QueryParam("title") String title,
			@QueryParam("author") String author,
			@DefaultValue("0") @QueryParam("minPrice") BigDecimal minPrice,
			@DefaultValue("9999") @QueryParam("maxPrice") BigDecimal maxPrice);

}