package edu.luc.fall2014.comp433.project.rest.representation;

import java.math.BigDecimal;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import edu.luc.fall2014.comp433.project.model.Payment;
import edu.luc.fall2014.comp433.project.model.enumerator.PaymentType;

public class PaymentRepresentation extends BaseRepresentation {

	private Short id;
	private OrderRepresentation order;
	private BigDecimal amount;
	private PaymentType type;
	private String cardNumber;
	private String cardHolderName;
	private int expirationMonth;
	private int expirationYear;
	private int securityCode;

	public PaymentRepresentation(Payment entity, BookstoreURI uri) {
		super();
		populateFields(entity, uri);
	}

	private void populateFields(Payment entity, BookstoreURI uri) {
		if (null != entity) {
			this.setAmount(entity.getAmount());
			this.setCardHolderName(entity.getCardHolderName());
			this.setCardNumber(entity.getCardNumber());
			this.setExpirationMonth(entity.getExpirationMonth());
			this.setExpirationYear(entity.getExpirationYear());
			this.setId(entity.getId());
			this.setSecurityCode(entity.getSecurityCode());
			this.setType(entity.getType());
			createLinks(entity, uri);
		}
	}

	private void createLinks(Payment entity, BookstoreURI uri) {
		// TODO review links
		addLink(new Link("self", uri.getPaymentPath(getId().toString()),
				HttpMethod.GET, MediaType.APPLICATION_JSON));
		addLink(new Link("order", uri.getOrderPath(entity.getOrder().getId()
				.toString()), HttpMethod.GET, MediaType.APPLICATION_JSON));
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@JsonIgnore
	@XmlTransient
	public OrderRepresentation getOrder() {
		return order;
	}

	public void setOrder(OrderRepresentation order) {
		this.order = order;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

}
