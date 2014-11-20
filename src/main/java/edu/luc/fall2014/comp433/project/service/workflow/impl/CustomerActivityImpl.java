/**
 * 
 */
package edu.luc.fall2014.comp433.project.service.workflow.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.luc.fall2014.comp433.project.dao.CustomerDao;
import edu.luc.fall2014.comp433.project.model.Address;
import edu.luc.fall2014.comp433.project.model.Customer;
import edu.luc.fall2014.comp433.project.service.exception.InvalidAddressException;
import edu.luc.fall2014.comp433.project.service.workflow.CustomerActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
@Stateless
public class CustomerActivityImpl extends
		BaseActivityImpl<Short, Customer, CustomerDao> implements
		CustomerActivity {

	@EJB
	private CustomerDao customerDao;

	@Override
	public Customer findCustomerByLogin(String login) {
		return customerDao.findByLogin(login);
	}

	@Override
	public Customer create(Customer customer) throws InvalidAddressException {
		return createOrUpdate(customer);
	}

	@Override
	public Customer update(Customer customer) throws InvalidAddressException {
		return createOrUpdate(customer);
	}

	@Override
	public Customer createOrUpdate(Customer customer)
			throws InvalidAddressException {
		validateCustomer(customer);
		createCustomerAddressBackRelation(customer);
		if (!customer.isPersisted()) {
			customerDao.persist(customer);
		}
		customer = customerDao.merge(customer);
		return customer;
	}

	/**
	 * @param customer
	 * @return
	 */
	@Override
	public boolean validateUserAuth(Customer customer) {
		return customerDao.validateUserAuthentication(customer.getLogin(),
				customer.getPassword());
	}

	private void validateCustomer(Customer customer)
			throws InvalidAddressException {
		if (null == customer)
			new IllegalAccessException("customer cannot be null.");

		List<Address> addresses = customer.getAddressList();
		if (null == customer.getAddressList() || addresses.isEmpty())
			throw new InvalidAddressException();
	}

	private void createCustomerAddressBackRelation(Customer customer) {
		for (Address address : customer.getAddressList()) {
			address.setCustomer(customer);
		}
	}

}
