package edu.luc.fall2014.comp433.project.rest.representation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import edu.luc.fall2014.comp433.project.model.Book;
import edu.luc.fall2014.comp433.project.model.Order;
import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;

@XmlRootElement
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
			this.setCustomerLogin(entity.getCustomer().getLogin().toString());
			this.setAddress(new AddressRepresentation(entity.getAddress(), uri));
			this.setPayment(new PaymentRepresentation(entity.getPayment(), uri));
			this.setBooks(fromModelList(entity.getBookList(), uri));
			this.setStatus(entity.getStatus());
			createLinks(uri);
		}
	}

	private void createLinks(BookstoreURI uri) {
		addLink(new Link("customer", uri.getCustomerPath(getCustomerLogin()),
				HttpMethod.GET));

		if (!OrderStatus.CANCELED.equals(getStatus())) {

			addLink(new Link("self", uri.getOrderPath(getId().toString()),
					HttpMethod.GET));
			addLink(new Link("status", uri.getOrderStatusPath(getId()
					.toString()), HttpMethod.GET));

			if (OrderStatus.PROCESSING.equals(getStatus())) {

				addLink(new Link("cancel", uri.getCancelOrderPath(getId()
						.toString()), HttpMethod.PUT));

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

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@JsonIgnore
	@XmlTransient
	public String getCustomerLogin() {
		return customerLogin;
	}

	public void setCustomerLogin(String customerLogin) {
		this.customerLogin = customerLogin;
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
