/**
 *
 */
package edu.luc.fall2014.comp433.project.service.workflow.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.luc.fall2014.comp433.project.dao.AddressDao;
import edu.luc.fall2014.comp433.project.model.Address;
import edu.luc.fall2014.comp433.project.service.workflow.AddressActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
@Stateless
public class AddressActivityImpl extends
		BaseActivityImpl<Short, Address, AddressDao> implements AddressActivity {

	@EJB
	private AddressDao addressDao;

	/**
	 * 
	 * @param addressId
	 * @return
	 */
	@Override
	public Address findAddressById(Short addressId) {
		return addressDao.findById(addressId);
	}

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	@Override
	public List<Address> findAddressByCustomerId(Short customerId) {
		return addressDao.findAddressByCustomerId(customerId);
	}

	@Override
	public Address findAddressByCustomerIdAndAddressInformation(
			Short customerId, Address address) {
		return addressDao.findAddressByCustomerIdAndAddressInformation(
				customerId, address);
	}

}
