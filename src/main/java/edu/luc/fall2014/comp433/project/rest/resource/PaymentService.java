/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource;

import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
public interface PaymentService extends BaseService<Short, Payment> {

	public Response findPaymentById(Short paymentId);

	public Response findPaymentByCustomerId(Short customerId);

}
