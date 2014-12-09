/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.luc.fall2014.comp433.project.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import edu.luc.fall2014.comp433.project.model.enumerator.PaymentType;

/**
 * 
 * @author Thiago Vieira Puluceno
 */
@Entity
@Table(schema = "bookstore")
@NamedQueries({
		@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
		@NamedQuery(name = "Payment.findById", query = "SELECT p FROM Payment p WHERE p.id = :id"),
		@NamedQuery(name = "Payment.findByType", query = "SELECT p FROM Payment p WHERE p.type = :type"),
		@NamedQuery(name = "Payment.findByAmount", query = "SELECT p FROM Payment p WHERE p.amount = :amount") })
public class Payment extends BaseEntity<Short> {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@NotNull
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	private PaymentType type;

	@Min(value = 0)
	@Max(value = 99999)
	@Basic(optional = false)
	private BigDecimal amount;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
			CascadeType.REFRESH })
	private Order order;

	@Basic(optional = true)
	private String cardNumber;

	@Basic(optional = true)
	private String cardHolderName;

	@Basic(optional = true)
	private int expirationMonth;

	@Basic(optional = true)
	private int expirationYear;

	@Basic(optional = true)
	private int securityCode;

	public Payment() {
	}

	public Payment(Short id) {
		this.id = id;
	}

	public Payment(Short id, PaymentType type, BigDecimal amount) {
		this.id = id;
		this.type = type;
		this.amount = amount;
	}

	@Override
	public Short getId() {
		return id;
	}

	@Override
	public void setId(Short id) {
		this.id = id;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName
	 *            the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the expirationMonth
	 */
	public int getExpirationMonth() {
		return expirationMonth;
	}

	/**
	 * @param expirationMonth
	 *            the expirationMonth to set
	 */
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	/**
	 * @return the expirationYear
	 */
	public int getExpirationYear() {
		return expirationYear;
	}

	/**
	 * @param expirationYear
	 *            the expirationYear to set
	 */
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	/**
	 * @return the securityCode
	 */
	public int getSecurityCode() {
		return securityCode;
	}

	/**
	 * @param securityCode
	 *            the securityCode to set
	 */
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Payment other = (Payment) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "model.Payment[ id=" + id + " ]";
	}

}
