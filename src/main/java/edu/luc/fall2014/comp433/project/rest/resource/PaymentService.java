/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
@Path("/payments")
public interface PaymentService extends BaseService<Short, Payment> {

	@GET
	@Path("{paymentId}")
	@Produces({ "application/json" })
	public Response findPaymentById(@PathParam("paymentId") Short paymentId);

	@GET
	@Produces({ "application/json" })
	@Path("/customers/{customerId}")
	public Response findPaymentByCustomerId(
			@PathParam("customerId") Short customerId);

}
