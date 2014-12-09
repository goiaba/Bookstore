package edu.luc.fall2014.comp433.project.rest.representation;

import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import edu.luc.fall2014.comp433.project.model.Address;

@XmlRootElement
public class AddressRepresentation extends BaseRepresentation {

	private Short id;
	private String street;
	private String complement;
	private String number;
	private String city;
	private String state;
	private int zipcode;
	private CustomerRepresentation customer;
	private List<OrderRepresentation> orders;

	public AddressRepresentation(Address entity, BookstoreURI uri) {
		super();
		populateFields(entity, uri);
	}

	private void populateFields(Address entity, BookstoreURI uri) {
		if (null != entity) {
			this.setId(entity.getId());
			this.setStreet(entity.getStreet());
			this.setNumber(entity.getNumber());
			this.setComplement(entity.getComplement());
			this.setCity(entity.getCity());
			this.setState(entity.getState());
			this.setZipcode(entity.getZipcode());
			createLinks(entity, uri);
		}
	}

	private void createLinks(Address entity, BookstoreURI uri) {
		addLink(new Link("self", uri.getBaseUri() + "/addresses/" + getId(),
				HttpMethod.GET));
		addLink(new Link("customer", uri.getCustomerPath(entity.getCustomer()
				.getLogin()), HttpMethod.GET));
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	@JsonIgnore
	@XmlTransient
	public CustomerRepresentation getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerRepresentation customer) {
		this.customer = customer;
	}

	@JsonIgnore
	@XmlTransient
	public List<OrderRepresentation> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderRepresentation> orders) {
		this.orders = orders;
	}

}
