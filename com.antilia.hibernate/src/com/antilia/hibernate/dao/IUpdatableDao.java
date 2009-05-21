package com.antilia.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IUpdatableDao<E extends Serializable> extends Serializable {
	 
	public E add(E element);

		
	public void addAll(Collection<E> element);

		
	
	public E remove(E element);

	 
	public void removeAll(Collection<E> element);

	 
	public void update(E element);

	public void updateAll(Collection<E> element);
	
}
