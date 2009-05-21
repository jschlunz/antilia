/**
 * 
 */
package com.antilia.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;

import com.antilia.hibernate.command.DefaultCommander;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class HibernateQuerableUpdatableDao<E extends Serializable> extends HibernateQuerableDao<E> implements IQuerableUpdatableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public HibernateQuerableUpdatableDao() {
	}

	public E add(E element) {
		return DefaultCommander.persist(element);		
	}

	public void addAll(Collection<E> element) {
		 DefaultCommander.persistAll(element);		
	}
	
	public E remove(E element) {
		 return DefaultCommander.delete(element);
	}
		 
	 public void removeAll(Collection<E> element) {
		 DefaultCommander.deleteAll(element);
	 }
	 
	 public void update(E element) {
		 DefaultCommander.update(element);
	 }
	 
	 public void updateAll(Collection<E> element) {
		 DefaultCommander.updateAll(element);
	 }

}
