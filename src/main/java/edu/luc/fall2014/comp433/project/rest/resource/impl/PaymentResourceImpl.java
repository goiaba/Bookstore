/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.fall2014.comp433.project.model.Payment;
import edu.luc.fall2014.comp433.project.rest.resource.PaymentService;
import edu.luc.fall2014.comp433.project.service.workflow.PaymentActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/payments")
public class PaymentResourceImpl implements PaymentService {

	@Inject
	private PaymentActivity paymentActivity;

	@Override
	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Response findPaymentById(@PathParam("id") Short id) {
		Response response = null;
		if (null != id) {
			Payment payment = paymentActivity.findPaymentById(id);
			if (null != payment) {
				response = Response.ok(payment).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}

	@Override
	@GET
	@Produces({ "application/json" })
	@Path("/customers/{customerId}")
	public Response findPaymentByCustomerId(
			@PathParam("customerId") Short customerId) {
		Response response = null;
		if (null != customerId) {
			List<Payment> payments = paymentActivity
					.findPaymentByCustomerId(customerId);
			if (null != payments && !payments.isEmpty()) {
				response = Response.ok(payments).build();
			} else {
				response = Response.status(Status.NOT_FOUND).build();
			}
		} else {
			response = Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}

}
