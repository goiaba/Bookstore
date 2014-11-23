/**
 *
 */
package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Address;
import edu.luc.fall2014.comp433.project.rest.representation.AddressRepresentation;
import edu.luc.fall2014.comp433.project.rest.representation.BookstoreURI;
import edu.luc.fall2014.comp433.project.rest.resource.AddressService;
import edu.luc.fall2014.comp433.project.service.workflow.AddressActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
public class AddressResourceImpl extends BaseResourceImpl<Short, Address>
		implements AddressService {

	@EJB
	AddressActivity addressActivity;

	@Override
	public Response findAddressById(Short addressId) {
		Response response = notFound();
		Address address = null;
		if (addressId != null) {
			 address = addressActivity.findById(addressId);
			// response = ok(address);
			// TODO enable address representation
			AddressRepresentation addRep = new AddressRepresentation(address,
					new BookstoreURI(getRequestUri()));
			response = ok(addRep);
		}
		return response;
	}

	@Override
	
	public Response findAddressByCustomerId(Short customerId) {
		Response response = notFound();
		List<Address> addresses = null;
		if (customerId != null) {
			// addresses = addressActivity.findAddressByCustomerId(customerId);
			// response = ok(addresses);
			// TODO enable address representation
			List<AddressRepresentation> addRepList = AddressRepresentation
					.fromModelList(AddressRepresentation.class, Address.class,
							addresses, new BookstoreURI(getRequestUri()));
			response = ok(addRepList);
		}
		return response;
	}
}
