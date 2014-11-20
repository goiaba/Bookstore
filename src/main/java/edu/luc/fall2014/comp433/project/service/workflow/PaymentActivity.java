package edu.luc.fall2014.comp433.project.service.workflow;

import java.util.List;

import edu.luc.fall2014.comp433.project.model.Payment;

public interface PaymentActivity extends BaseActivity<Short, Payment> {

	Payment findPaymentById(Short paymentId);

	List<Payment> findPaymentByCustomerId(Short customerId);

}
