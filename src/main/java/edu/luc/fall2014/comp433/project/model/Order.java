package edu.luc.fall2014.comp433.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.luc.fall2014.comp433.project.model.enumerator.OrderStatus;

/**
 * 
 * @author Thiago Vieira Puluceno
 */
@Entity(name = "Order_")
@Table(schema = "bookstore")
@NamedQueries({
		@NamedQuery(name = "OrderRepresentation.findAll", query = "SELECT o FROM Order_ o"),
		@NamedQuery(name = "OrderRepresentation.findById", query = "SELECT o FROM Order_ o WHERE o.id = :id"),
		@NamedQuery(name = "OrderRepresentation.findByStatus", query = "SELECT o FROM Order_ o WHERE o.status = :status") })
public class Order extends BaseEntity<Short> {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@NotNull
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(schema = "bookstore", name = "Order_Detail", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id"))
	private List<Book> bookList = new ArrayList<Book>();

	@JoinColumn(name = "customer", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {
			CascadeType.DETACH, CascadeType.REFRESH })
	private Customer customer;

	@JoinColumn(name = "payment", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Payment payment;

	@JoinColumn(name = "address", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {
			CascadeType.REFRESH, CascadeType.MERGE })
	private Address address;

	public Order() {
	}

	public Order(Short id) {
		this.id = id;
	}

	public Order(Short id, OrderStatus status) {
		this.id = id;
		this.status = status;
	}

	public Order(Customer customer, Address address, List<Book> bookList,
			Payment payment) {
		this.customer = customer;
		this.address = address;
		this.bookList = bookList;
		this.payment = payment;
	}

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Short id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "model.Order[ id=" + id + " ]";
	}

}
