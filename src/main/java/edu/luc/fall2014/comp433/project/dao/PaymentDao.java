/**
 *
 */
package edu.luc.fall2014.comp433.project.dao;

import java.util.List;

import edu.luc.fall2014.comp433.project.model.Payment;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
public interface PaymentDao extends BaseDao<Short, Payment> {

	List<Payment> findPaymentsByCustomer(Short customerId);

}
