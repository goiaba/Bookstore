/**
 * 
 */
package edu.luc.fall2014.comp433.project.dao;

import java.util.List;

import edu.luc.fall2014.comp433.project.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface AddressDao extends BaseDao<Short, Address> {

	/**
	 * @param customerId
	 * @return
	 */
	List<Address> findAddressByCustomerId(Short customerId);
	
	List<Short> findAddressIdByCustomerId(Short customerId);

	Address findAddressByCustomerIdAndAddressInformation(Short customerId,
			Address address);

}
