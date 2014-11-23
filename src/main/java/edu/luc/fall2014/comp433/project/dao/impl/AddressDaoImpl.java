package edu.luc.fall2014.comp433.project.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import edu.luc.fall2014.comp433.project.dao.AddressDao;
import edu.luc.fall2014.comp433.project.model.Address;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
@Stateless
public class AddressDaoImpl extends BaseDaoImpl<Short, Address> implements
		AddressDao {

	public AddressDaoImpl() {
		super(Address.class);
	}

	@Override
	public List<Address> findAddressByCustomerId(Short customerId) {
		String query = "SELECT address FROM Address "
				+ "address WHERE address.customer.id= :customerId";
		try {
			return getEntityManager().createQuery(query, Address.class)
					.setParameter("customerId", customerId).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Short> findAddressIdByCustomerId(Short customerId) {
		String query = "SELECT id FROM Address "
				+ "address WHERE address.customer.id= :customerId";
		try {
			return getEntityManager().createQuery(query, Short.class)
					.setParameter("customerId", customerId).getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Address findAddressByCustomerIdAndAddressInformation(
			Short customerId, Address address) {
		String query = "SELECT address FROM Address "
				+ "address WHERE address.customer.id= :customerId "
				+ "AND address.street= :street "
				+ "AND address.complement= :complement "
				+ "AND address.number= :number "
				+ "AND address.zipcode= :zipcode "
				+ "AND address.complement= :complement "
				+ "AND address.city= :city " + "AND address.state= :state";

		try {
			return getEntityManager().createQuery(query, Address.class)
					.setParameter("customerId", customerId)
					.setParameter("street", address.getStreet())
					.setParameter("complement", address.getComplement())
					.setParameter("number", address.getNumber())
					.setParameter("zipcode", address.getZipcode())
					.setParameter("city", address.getCity())
					.setParameter("state", address.getState())
					.getSingleResult();
		} catch (NoResultException e) {
			return address;
		}
	}

}
