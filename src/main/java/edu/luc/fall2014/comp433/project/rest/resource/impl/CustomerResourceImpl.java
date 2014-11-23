/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.net.URI;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Customer;
import edu.luc.fall2014.comp433.project.rest.representation.BookstoreURI;
import edu.luc.fall2014.comp433.project.rest.representation.CustomerRepresentation;
import edu.luc.fall2014.comp433.project.rest.resource.CustomerService;
import edu.luc.fall2014.comp433.project.service.exception.InvalidAddressException;
import edu.luc.fall2014.comp433.project.service.workflow.AddressActivity;
import edu.luc.fall2014.comp433.project.service.workflow.CustomerActivity;
import edu.luc.fall2014.comp433.project.service.workflow.OrderActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
public class CustomerResourceImpl extends BaseResourceImpl<Short, Customer>
		implements CustomerService {

	@EJB
	private CustomerActivity customerActivity;

	@EJB
	private AddressActivity addressActivity;

	@EJB
	private OrderActivity orderActivity;

	@Override
	public Response retrieveByLogin(String login) {
		Response response = notFound();
		Customer customer = null;
		if (null != login) {
			customer = customerActivity.findByLogin(login);
			if (null != customer) {
				// response = ok(customer);
				// TODO enable customer representation
				CustomerRepresentation custRep = new CustomerRepresentation(
						customer, new BookstoreURI(getRequestUri()));
				custRep.addAddressesLinks(addressActivity
						.findAddressIdByCustomerId(customer.getId()));
				custRep.addOrdersLinks(orderActivity
						.findOrderIdByCustomerLogin(customer.getLogin()));
				response = ok(custRep);
			}
		}
		return response;
	}

	// @Override
	// public Response retrieveById(Short customerId) {
	// Response response = notFound();
	// Customer customer = null;
	// if (null != customerId) {
	// customer = customerActivity.findByCustomerId(customerId);
	// if (null != customer) {
	// // response = ok(customer);
	// // TODO enable customer representation
	// CustomerRepresentation custRep = new CustomerRepresentation(
	// customer, new BookstoreURI(getRequestUri()));
	// custRep.addAddressesLinks(addressActivity
	// .findAddressIdByCustomerId(customer.getId()));
	// custRep.addOrdersLinks(orderActivity
	// .findOrderIdByCustomerLogin(customer.getLogin()));
	// response = ok(custRep);
	// }
	// }
	// return response;
	// }

	@Override
	public Response create(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.create(customer);
			// response = created(URI.create("/customers/" +
			// customer.getLogin()),
			// customer);
			// TODO enable customer representation
			CustomerRepresentation custRep = new CustomerRepresentation(
					customer, new BookstoreURI(getRequestUri()));
			response = created(URI.create("/customers/" + customer.getLogin()),
					custRep);
		} catch (InvalidAddressException e) {
			response = badRequest();
		} catch (Exception e) {
			e.printStackTrace();
			response = internalServerError();
		}
		return response;
	}

	@Override
	public Response update(Short customerId, Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.update(customer);
			// response = ok(customer);
			// TODO enable customer representation
			CustomerRepresentation custRep = new CustomerRepresentation(
					customer, new BookstoreURI(getRequestUri()));
			response = ok(custRep);
		} catch (InvalidAddressException e) {
			response = badRequest();
		} catch (Exception e) {
			e.printStackTrace();
			response = internalServerError();
		}
		return response;
	}

}
