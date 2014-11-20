/**
 * 
 */
package edu.luc.fall2014.comp433.project.service.workflow.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.luc.fall2014.comp433.project.dao.OrderDao;
import edu.luc.fall2014.comp433.project.model.Address;
import edu.luc.fall2014.comp433.project.model.Book;
import edu.luc.fall2014.comp433.project.model.Customer;
import edu.luc.fall2014.comp433.project.model.Order;
import edu.luc.fall2014.comp433.project.model.Payment;
import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;
import edu.luc.fall2014.comp433.project.service.exception.InvalidAddressException;
import edu.luc.fall2014.comp433.project.service.exception.OrderNotFoundException;
import edu.luc.fall2014.comp433.project.service.workflow.AddressActivity;
import edu.luc.fall2014.comp433.project.service.workflow.CustomerActivity;
import edu.luc.fall2014.comp433.project.service.workflow.OrderActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
@Stateless
public class OrderActivityImpl extends BaseActivityImpl<Short, Order, OrderDao>
		implements OrderActivity {

	@EJB
	private OrderDao orderDao;

	@EJB
	private CustomerActivity customerActivity;

	@EJB
	private AddressActivity addressActivity;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Order createOrder(Order order) throws InvalidAddressException {

		Customer customer = order.getCustomer();
		Address address = order.getAddress();
		Payment payment = order.getPayment();

		/*
		 * Verifies if the customer is already registered. In this case we try
		 * to reuse an existing address to delivery.
		 */
		if (customerActivity.validateUserAuth(customer)) {
			customer = customerActivity
					.findCustomerByLogin(customer.getLogin());
			address = addressActivity
					.findAddressByCustomerIdAndAddressInformation(
							customer.getId(), order.getAddress());

			/* If it is a new address, update customer's address list */
			if (!address.isPersisted()) {
				customer.getAddressList().add(address);
				address.setCustomer(customer);
				customer = customerActivity.update(customer);
			}
		} else {
			customer.getAddressList().add(address);
			address.setCustomer(customer);
			customer = customerActivity.create(customer);
		}

		/*
		 * Retrieves the attached instance of the address to be used as the
		 * order delivery address
		 */
		address = addressActivity.findAddressByCustomerIdAndAddressInformation(
				customer.getId(), address);

		// Order -> Customer order.setCustomer(...); customer.addOrder(...)
		order.setCustomer(customer);
		customer.getOrderList().add(order);
		// Order -> Address order.setAddress(...); address.addOrder(...)
		order.setAddress(address);
		address.getOrderList().add(order);
		// Order -> Payment order.setPayment(...); payment.setOrder(...)
		payment.setOrder(order);
		order.setPayment(payment);
		// Order -> Book order.addBook(...); book.addOrder(...)
		/*
		 * Computing the total amount of the order
		 */
		BigDecimal amount = new BigDecimal(0);
		for (Book book : order.getBookList()) {
			amount = amount.add(book.getPrice());
		}
		order.getPayment().setAmount(amount);
		order.setStatus(OrderStatus.PROCESSING);
		orderDao.persist(order);
		customerActivity.update(customer);

		return orderDao.merge(order);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean cancelOrder(Short orderId) throws OrderNotFoundException {
		Order toCancel = orderDao.findById(orderId);
		if (null == toCancel)
			throw new OrderNotFoundException();
		if (OrderStatus.PROCESSING.equals(toCancel.getStatus())) {
			toCancel.setStatus(OrderStatus.CANCELED);
			orderDao.merge(toCancel);
			return true;
		}
		return false;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public OrderStatus checkOrderStatus(Short orderId)
			throws OrderNotFoundException {
		Order toCheck = orderDao.findById(orderId);
		if (null == toCheck)
			throw new OrderNotFoundException();
		return toCheck.getStatus();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Order> findOrderByCustomerLogin(String login)
			throws OrderNotFoundException {
		List<Order> orders = orderDao.findOrderByCustomerLogin(login);
		if (null == orders || orders.isEmpty())
			throw new OrderNotFoundException();
		// TODO: Ugly way of removing customer info going to client. Improve it.
		for (Order order : orders) {
			order.setCustomer(null);
		}
		return orders;
	}

}
