package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import edu.luc.fall2014.comp433.project.model.Order;
import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;
import edu.luc.fall2014.comp433.project.rest.representation.BookstoreURI;
import edu.luc.fall2014.comp433.project.rest.representation.OrderRepresentation;
import edu.luc.fall2014.comp433.project.rest.resource.OrderService;
import edu.luc.fall2014.comp433.project.service.exception.OrderNotFoundException;
import edu.luc.fall2014.comp433.project.service.workflow.OrderActivity;

/**
 * 
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
public class OrderResourceImpl extends BaseResourceImpl<Short, Order> implements
		OrderService {

	@EJB
	private OrderActivity orderActivity;

	@Override
	public Response retrieveOrder(Short orderId) {
		Response response = notFound();
		try {
			if (null != orderId) {
				Order order = orderActivity.retrieveOrder(orderId);
				if (null != order) {
					// response = ok(order);
					// TODO enable order representation
					OrderRepresentation orderRep = new OrderRepresentation(
							order, new BookstoreURI(getRequestUri()));
					response = ok(orderRep);
				}
			}
		} catch (OrderNotFoundException e) {
			response = notFound();
		}
		return response;
	}

	@Override
	public Response retrieveOrdersByCustomerId(Short customerId) {
		Response response = notFound();
		try {
			if (null != customerId) {
				List<Order> orders = orderActivity
						.findByCustomerId(customerId);
				if (null != orders && !orders.isEmpty()) {
					List<OrderRepresentation> orderRepList = OrderRepresentation
							.fromModelList(OrderRepresentation.class,
									Order.class, orders, new BookstoreURI(
											getRequestUri()));
					response = ok(orderRepList);
				}
			}
		} catch (OrderNotFoundException e) {
			response = notFound();
		}
		return response;
	}
	
//	@Override
//	public Response findOrderByCustomerLogin(String login) {
//		Response response = null;
//		List<Order> orders = null;
//		try {
//			orders = orderActivity.findByCustomerLogin(login);
//			List<OrderRepresentation> orderRepList = OrderRepresentation
//					.fromModelList(OrderRepresentation.class, Order.class,
//							orders, new BookstoreURI(getRequestUri()));
//			response = ok(orderRepList);
//		} catch (OrderNotFoundException e) {
//			response = notFound();
//		}
//		return response;
//	}

	@Override
	public Response createOrder(Order order) {
		Response response = null;
		try {
			if (null != order) {
				order = orderActivity.createOrder(order);
				OrderRepresentation orderRep = new OrderRepresentation(order,
						new BookstoreURI(getRequestUri()));
				response = created(orderRep.getSelfLinkUri(), orderRep);
			} else {
				response = notFound();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = internalServerError();
		}
		return response;
	}

	@Override
	public Response cancelOrder(Short orderId) {
		Response response = null;
		try {
			if (null != orderId) {
				boolean orderCancelled = orderActivity.cancelOrder(orderId);
				response = ok(orderCancelled);
			} else {
				response = notFound();
			}
		} catch (OrderNotFoundException e) {
			response = notFound();
		} catch (Exception e) {
			response = internalServerError();
		}
		return response;
	}

	@Override
	public Response checkOrderStatus(Short orderId) {
		Response response = null;
		try {
			if (null != orderId) {
				OrderStatus status = orderActivity.checkOrderStatus(orderId);
				response = ok(status);
			} else {
				response = notFound();
			}
		} catch (OrderNotFoundException e) {
			response = notFound();
		}
		return response;
	}

}
