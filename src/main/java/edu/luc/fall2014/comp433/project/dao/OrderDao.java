/**
 * 
 */
package edu.luc.fall2014.comp433.project.dao;

import java.util.List;

import edu.luc.fall2014.comp433.project.model.Order;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface OrderDao extends BaseDao<Short, Order> {

	/**
	 * @param login
	 * @return
	 */
	List<Order> findOrderByCustomerLogin(String login);

}

