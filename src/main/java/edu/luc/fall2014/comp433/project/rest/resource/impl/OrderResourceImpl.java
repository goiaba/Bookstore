package edu.luc.fall2014.comp433.project.rest.resource.impl;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.luc.fall2014.comp433.project.model.Order;
import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;
import edu.luc.fall2014.comp433.project.rest.resource.OrderService;
import edu.luc.fall2014.comp433.project.service.exception.OrderNotFoundException;
import edu.luc.fall2014.comp433.project.service.workflow.OrderActivity;

/**
 * 
 * @author Thiago Puluceno <tpuluceno@luc.edu>
 * 
 */
public class OrderResourceImpl implements OrderService {

	@EJB
	private OrderActivity orderActivity;

	@Override
	public Response createOrder(Order order) {
		Response response = null;
		try {
			if (null != order) {
				order = orderActivity.createOrder(order);
				response = Response
						.created(URI.create("/orders/" + order.getId()))
						.entity(order).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Override
	public Response cancelOrder(Short orderId) {
		Response response = null;
		try {
			if (null != orderId) {
				boolean orderCancelled = orderActivity.cancelOrder(orderId);
				response = Response.ok().entity(orderCancelled).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Override
	public Response checkOrderStatus(Short orderId) {
		Response response = null;
		try {
			if (null != orderId) {
				OrderStatus status = orderActivity.checkOrderStatus(orderId);
				response = Response.status(Status.OK).entity(status).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).build();
			}
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;
	}

	@Override
	public Response findOrderByCustomerLogin(String login) {
		Response response = null;
		List<Order> orders = null;
		try {
			orders = orderActivity.findOrderByCustomerLogin(login);
			response = Response.ok().entity(orders).build();
		} catch (OrderNotFoundException e) {
			response = Response.status(Status.NOT_FOUND).build();
		}
		return response;
	}

}
