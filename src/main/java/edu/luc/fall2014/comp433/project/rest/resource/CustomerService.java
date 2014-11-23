/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Customer;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
@Path("/customers")
public interface CustomerService extends BaseService<Short, Customer> {

	/**
	 * Method responsible for returning customer data once his login is
	 * provided.
	 * 
	 * @param login
	 *            User login
	 * @return All customer data.
	 */
	@GET
	@Path("/{login}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveByLogin(@PathParam("login") String login);

//	@GET
//	@Path("/{customerId}")
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response retrieveById(@PathParam("customerId") Short customerId);

	/**
	 * @param customer
	 * @param address
	 * @param payment
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response create(Customer customer);

	@PUT
	@Path("/{customerId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response update(@PathParam("customerId") Short customerId,
			Customer customer);

}
