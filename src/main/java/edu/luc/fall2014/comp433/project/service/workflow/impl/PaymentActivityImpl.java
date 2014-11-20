/**
 * 
 */
package edu.luc.fall2014.comp433.project.service.workflow.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.luc.fall2014.comp433.project.dao.PaymentDao;
import edu.luc.fall2014.comp433.project.model.Payment;
import edu.luc.fall2014.comp433.project.service.workflow.PaymentActivity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
@Stateless
public class PaymentActivityImpl extends
		BaseActivityImpl<Short, Payment, PaymentDao> implements PaymentActivity {

	@EJB
	private PaymentDao paymentDao;

	@Override
	public Payment findPaymentById(Short paymentId) {
		return paymentDao.findById(paymentId);
	}

	@Override
	public List<Payment> findPaymentByCustomerId(Short customerId) {
		return paymentDao.findPaymentsByCustomer(customerId);
	}

}
