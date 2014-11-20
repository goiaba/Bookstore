package edu.luc.fall2014.comp433.project.service.workflow;

import java.util.List;

import edu.luc.fall2014.comp433.project.model.Address;

public interface AddressActivity extends BaseActivity<Short, Address> {

	Address findAddressById(Short addressId);

	List<Address> findAddressByCustomerId(Short customerId);
	
	Address findAddressByCustomerIdAndAddressInformation(
			Short customerId, Address address);

}
