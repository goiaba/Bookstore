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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.fall2014.comp433.project.model.Address;
import edu.luc.fall2014.comp433.project.rest.resource.AddressService;
import edu.luc.fall2014.comp433.project.service.workflow.AddressActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
@Path("/addresses")
public class AddressResourceImpl implements AddressService {

	@Inject
	AddressActivity addressActivity;

	@Override
	@GET
	@Path("/{addressId}")
	@Produces({ "application/json" })
	public Response findAddressById(@PathParam("addressId") Short addressId) {
		if (addressId == null)
			throw new WebApplicationException(400);
		Address address = addressActivity.findAddressById(addressId);
		return Response.status(Status.OK).entity(address).build();
	}

	@Override
	@GET
	@Path("/customers/{customerId}")
	@Produces({ "application/json" })
	public Response findAddressByCustomerId(
			@PathParam("customerId") Short customerId) {
		if (customerId == null)
			throw new WebApplicationException(400);
		List<Address> addresses = addressActivity
				.findAddressByCustomerId(customerId);
		return Response.status(Status.OK).entity(addresses).build();
	}
}
