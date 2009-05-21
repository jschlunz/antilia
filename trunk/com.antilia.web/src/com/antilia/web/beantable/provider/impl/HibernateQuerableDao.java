/**
 * 
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.provider.IQuerableDao;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class HibernateQuerableDao<E extends Serializable> implements IQuerableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public HibernateQuerableDao() {
	}
	
	public List<E> findAll(IQuery<E> query) {
		return DefaultCommander.loadList(query);
	}
	
	public Long count(IQuery<E> query) {
		return DefaultCommander.count(query);
	}
	
	public E findById(Class<E> beanClass, Serializable key) {
		return DefaultCommander.findById(beanClass, key);
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
