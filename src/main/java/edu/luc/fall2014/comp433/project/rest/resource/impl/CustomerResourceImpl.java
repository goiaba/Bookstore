/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.fall2014.comp433.project.model.Customer;
import edu.luc.fall2014.comp433.project.rest.resource.CustomerService;
import edu.luc.fall2014.comp433.project.service.exception.InvalidAddressException;
import edu.luc.fall2014.comp433.project.service.workflow.CustomerActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public class CustomerResourceImpl implements CustomerService {

	@Inject
	private CustomerActivity customerActivity;

	@Override
	public Response findByLogin(String login) {
		Customer customer = null;
		Response response = Response.status(Status.NOT_FOUND).build();

		if (null != login) {
			customer = customerActivity.findCustomerByLogin(login);
			if (null != customer)
				response = Response.ok().entity(customer).build();
		}
		return response;
	}

	@Override
	public Response create(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.create(customer);
			response = Response
					.created(URI.create("/customers/" + customer.getLogin()))
					.entity(customer).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Override
	public Response update(Customer customer) {
		Response response = null;
		try {
			customer = customerActivity.update(customer);
			response = Response.ok().entity(customer).build();
		} catch (InvalidAddressException e) {
			response = Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
}
