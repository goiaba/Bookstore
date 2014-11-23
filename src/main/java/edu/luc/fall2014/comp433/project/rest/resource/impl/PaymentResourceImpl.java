/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Payment;
import edu.luc.fall2014.comp433.project.rest.representation.BookstoreURI;
import edu.luc.fall2014.comp433.project.rest.representation.PaymentRepresentation;
import edu.luc.fall2014.comp433.project.rest.resource.PaymentService;
import edu.luc.fall2014.comp433.project.service.workflow.PaymentActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
public class PaymentResourceImpl extends BaseResourceImpl<Short, Payment>
		implements PaymentService {

	@EJB
	private PaymentActivity paymentActivity;

	@Override
	public Response findPaymentById(Short id) {
		Response response = notFound();
		if (null != id) {
			Payment payment = paymentActivity.findPaymentById(id);
			if (null != payment) {
				// response = ok(payment);
				// TODO enable payment representation
				PaymentRepresentation payRep = new PaymentRepresentation(
						payment, new BookstoreURI(getRequestUri()));
				response = ok(payRep);
			}
		}
		return response;
	}

	@Override
	public Response findPaymentByCustomerId(Short customerId) {
		Response response = notFound();
		if (null != customerId) {
			List<Payment> payments = paymentActivity
					.findPaymentByCustomerId(customerId);
			if (null != payments && !payments.isEmpty()) {
				// response = ok(payments);
				// TODO enable order representation
				List<PaymentRepresentation> payRepList = PaymentRepresentation
						.fromModelList(PaymentRepresentation.class,
								Payment.class, payments, new BookstoreURI(
										getRequestUri()));
				response = ok(payRepList);
			}
		}
		return response;
	}

}
