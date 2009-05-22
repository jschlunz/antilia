/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.query.IQuery;

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
}
