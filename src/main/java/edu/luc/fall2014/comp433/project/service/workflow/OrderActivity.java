package edu.luc.fall2014.comp433.project.service.workflow;

import java.util.List;

import edu.luc.fall2014.comp433.project.model.Order;
import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;
import edu.luc.fall2014.comp433.project.service.exception.InvalidAddressException;
import edu.luc.fall2014.comp433.project.service.exception.OrderNotFoundException;

public interface OrderActivity extends BaseActivity<Short, Order> {

	Order retrieveOrder(Short orderId) throws OrderNotFoundException;

	List<Order> findByCustomerId(Short customerId)
			throws OrderNotFoundException;

	List<Order> findByCustomerLogin(String login) throws OrderNotFoundException;

	List<Short> findOrderIdByCustomerLogin(String login);

	Order createOrder(Order order) throws InvalidAddressException;

	Boolean cancelOrder(Short orderId) throws OrderNotFoundException;

	OrderStatus checkOrderStatus(Short orderId) throws OrderNotFoundException;

}
