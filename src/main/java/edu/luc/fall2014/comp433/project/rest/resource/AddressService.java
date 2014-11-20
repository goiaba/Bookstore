/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource;

import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 *
 */
public interface AddressService extends BaseService<Short, Address> {

	public Response findAddressById(Short addressId);

	public Response findAddressByCustomerId(Short customerId);

}
