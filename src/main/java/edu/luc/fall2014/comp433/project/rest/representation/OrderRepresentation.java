package edu.luc.fall2014.comp433.project.rest.representation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import edu.luc.fall2014.comp433.project.model.Book;
import edu.luc.fall2014.comp433.project.model.Order;
import edu.luc.fall2014.comp433.project.model.Payment;
import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;
import edu.luc.fall2014.comp433.project.model.enumerator.PaymentType;

public class OrderRepresentation extends BaseRepresentation {

	private Short id;
	private String customerLogin;
	private AddressRepresentation address;
	private PaymentRepresentation payment;
	private List<BookRepresentation> books;
	private OrderStatus status;

	public OrderRepresentation(Order entity, BookstoreURI uri) {
		super();
		populateFields(entity, uri);
	}

	private void populateFields(Order entity, BookstoreURI uri) {
		if (null != entity) {
			this.setId(entity.getId());
			this.customerLogin = entity.getCustomer().getLogin().toString();
			this.setAddress(new AddressRepresentation(entity.getAddress(), uri));
			this.setPayment(new PaymentRepresentation(entity.getPayment(), uri));
			this.setBooks(fromModelList(entity.getBookList(), uri));
			this.setStatus(entity.getStatus());
			createLinks(uri);
		}
	}

	private void createLinks(BookstoreURI uri) {
		addLink(new Link("customer", uri.getCustomerPath(customerLogin),
				HttpMethod.GET, MediaType.APPLICATION_JSON));
		
		if (!OrderStatus.CANCELED.equals(getStatus())) {
		
			addLink(new Link("self", uri.getOrderPath(getId().toString()),
					HttpMethod.GET, MediaType.APPLICATION_JSON));
			addLink(new Link("status", uri.getOrderStatusPath(getId()
					.toString()), HttpMethod.GET, MediaType.APPLICATION_JSON));
			
			if (OrderStatus.PROCESSING.equals(getStatus())) {
			
				addLink(new Link("cancel", uri.getCancelOrderPath(getId()
						.toString()), HttpMethod.PUT,
						MediaType.APPLICATION_JSON));
				
			}
		}
	}

	private List<BookRepresentation> fromModelList(List<Book> bookList,
			BookstoreURI uri) {
		List<BookRepresentation> bookRepList = new ArrayList<BookRepresentation>();
		for (Book order : bookList) {
			BookRepresentation bookRep = new BookRepresentation(order, uri);
			bookRepList.add(bookRep);
		}
		return bookRepList;

	}

	public static void main(String[] args) {

		Payment p = new Payment(new Integer(1).shortValue(), PaymentType.CASH,
				new BigDecimal(100));
		Order o = new Order(null, null, null, p);
		o.setStatus(OrderStatus.PROCESSING);
		o.setId(new Integer(1).shortValue());
		p.setOrder(o);

		PaymentRepresentation pr = new PaymentRepresentation(p,
				new BookstoreURI("http://localhost:8080/project/rest"));
		System.out.println(pr.getOrder().getStatus());
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public AddressRepresentation getAddress() {
		return address;
	}

	public void setAddress(AddressRepresentation address) {
		this.address = address;
	}

	public PaymentRepresentation getPayment() {
		return payment;
	}

	public void setPayment(PaymentRepresentation payment) {
		this.payment = payment;
	}

	public List<BookRepresentation> getBooks() {
		return books;
	}

	public void setBooks(List<BookRepresentation> books) {
		this.books = books;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
