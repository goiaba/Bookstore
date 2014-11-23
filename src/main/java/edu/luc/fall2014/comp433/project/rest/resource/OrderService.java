package edu.luc.fall2014.comp433.project.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Order;

/**
 * 
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
@Path("/orders")
public interface OrderService extends BaseService<Short, Order> {

	/**
	 * Retrieves the most actual information of specific order 
	 * @param orderId the order identifier
	 * @return order representation
	 */
	@GET
	@Path("/{orderId:[0-9]+}")
	@Produces({ "application/json" })
	public Response retrieveOrder(@PathParam("orderId") Short orderId);
	
	@GET
	@Path("/customers/{customerId:[0-9]+}")
	@Produces({ "application/json" })
	public Response retrieveOrdersByCustomerId(@PathParam("customerId") Short customerId);
	
	/**
	 * Creates an order for the given customer.
	 * 
	 * @param customer
	 *            CustomerRepresentation who created the order
	 * @param address
	 *            AddressRepresentation to delivery
	 * @param books
	 *            Books in the order
	 * @param payment
	 *            PaymentRepresentation information
	 * @return Message to the customer informing whether the order has been
	 *         created successfully or not.
	 */
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response createOrder(Order order);

	/**
	 * Cancel an order
	 * 
	 * @param orderId
	 *            OrderRepresentation ID used to find the order an then cancel
	 *            it.
	 * @return true if order is cancelled, otherwise false.
	 */
	@PUT
	@Path("/{orderId:[0-9]+}/cancel")
	@Produces({ "application/json" })
	public Response cancelOrder(@PathParam("orderId") Short orderId);

	/**
	 * Check the order status for a given order id
	 * 
	 * @param orderId
	 *            OrderRepresentation ID used to find the order an display its
	 *            current status.
	 * @return Current order status.
	 */
	@GET
	@Path("/{orderId:[0-9]+}/status")
	@Produces({ "application/json" })
	public Response checkOrderStatus(@PathParam("orderId") Short orderId);

//	/**
//	 * Locate orders from specific customer
//	 * 
//	 * @param login
//	 *            Login of the customer
//	 * @return List of orders sorted by Status.
//	 */
//	@GET
//	@Path("/customers/{login}")
//	@Produces({ "application/json" })
//	public Response findOrderByCustomerLogin(@PathParam("login") String login);

}
