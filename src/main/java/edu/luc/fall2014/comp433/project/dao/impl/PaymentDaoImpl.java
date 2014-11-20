/**
 *
 */
package edu.luc.fall2014.comp433.project.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import edu.luc.fall2014.comp433.project.dao.PaymentDao;
import edu.luc.fall2014.comp433.project.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
@Stateless
public class PaymentDaoImpl extends BaseDaoImpl<Short, Payment> implements
		PaymentDao {

	public PaymentDaoImpl() {
		super(Payment.class);
	}

	@Override
	public List<Payment> findPaymentsByCustomer(Short customerId) {
		String query = "SELECT order_.payment FROM Order_ order_ " +
				"JOIN order_.customer customer WHERE customer.id = :customerId";
		try {
			return getEntityManager().createQuery(query, Payment.class)
					.setParameter("customerId", customerId).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
