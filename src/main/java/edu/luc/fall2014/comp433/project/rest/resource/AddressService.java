/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
@Path("/addresses")
public interface AddressService extends BaseService<Short, Address> {

	@GET
	@Path("/{addressId}")
	@Produces({ "application/json" })
	public Response findAddressById(@PathParam("addressId") Short addressId);

	@GET
	@Path("/customers/{customerId}")
	@Produces({ "application/json" })
	public Response findAddressByCustomerId(@PathParam("customerId") Short customerId);

}
