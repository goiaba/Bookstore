package edu.luc.fall2014.comp433.project.service.workflow;

import edu.luc.fall2014.comp433.project.model.Customer;
import edu.luc.fall2014.comp433.project.service.exception.InvalidAddressException;

public interface CustomerActivity extends BaseActivity<Short, Customer> {

	Customer findCustomerByLogin(String login);

	Customer create(Customer customer) throws InvalidAddressException;

	Customer update(Customer customer) throws InvalidAddressException;

	Customer createOrUpdate(Customer customer) throws InvalidAddressException;

	boolean validateUserAuth(Customer customer);
	
}
