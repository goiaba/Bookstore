package edu.luc.fall2014.comp433.project.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import edu.luc.fall2014.comp433.project.dao.OrderDao;
import edu.luc.fall2014.comp433.project.model.Order;

@Stateless
public class OrderDaoImpl extends BaseDaoImpl<Short, Order> implements OrderDao {

	public OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public List<Order> findOrderByCustomerLogin(String login) {
		String query = "SELECT customer.orderList FROM Customer "
				+ "customer WHERE customer.login = :login";
		try {
			return getEntityManager().createQuery(query, Order.class)
					.setParameter("login", login).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Short> findOrderIdByCustomerLogin(String login) {
		String query = "SELECT order_.id FROM Order_ order_ "
				+ "JOIN order_.customer customer WHERE customer.login = :login";
		try {
			return getEntityManager().createQuery(query, Short.class)
					.setParameter("login", login).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> findOrderByCustomerId(Short customerId) {
		String query = "SELECT customer.orderList FROM Customer "
				+ "customer WHERE customer.id= :customerId";
		try {
			return getEntityManager().createQuery(query)
					.setParameter("customerId", customerId).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

}
