/**
 * 
 */
package edu.luc.fall2014.comp433.project.dao;

import edu.luc.fall2014.comp433.project.model.Customer;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface CustomerDao extends BaseDao<Short, Customer> {

	/**
	 * @param login
	 * @return
	 */
	Customer findByLogin(String login);

	boolean validateUserAuthentication(String login, String password);

}
