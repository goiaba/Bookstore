package edu.luc.fall2014.comp433.project.dao;

import java.util.List;

import javax.persistence.EntityManager;

import edu.luc.fall2014.comp433.project.model.BaseEntity;

/**
 * @author Bruno Correa <brunogmc at gmail>
 * 
 */
public interface BaseDao<I extends Number, E extends BaseEntity<I>> {

	E findById(I id);

	List<E> findById(List<I> ids);

	List<E> findAll();
	
	List<E> findAll(int start, int max);
	
	Boolean exists(E entity);

	void persist(E entity);

	void remove(E entity);

	E merge(E entity);

	void detach(E entity);

	void flush();

	EntityManager getEntityManager();

}
