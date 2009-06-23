/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import com.antilia.common.dao.IQuerableUpdatableDao;
import com.antilia.hibernate.cfg.IPersistenceUnit;
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
		IPersistenceUnit old = setPersistenceUnit();
		try {
			return DefaultCommander.persist(element);
		} finally {
			restorePersistenceUnit(old);
		}
	}

	public void addAll(Collection<E> element) {
		IPersistenceUnit old = setPersistenceUnit();
		try {
			DefaultCommander.persistAll(element);
		} finally {
			restorePersistenceUnit(old);
		}		 		
	}
	
	public E remove(E element) {
		IPersistenceUnit old = setPersistenceUnit();
		try {
			return DefaultCommander.delete(element);
		} finally {
			restorePersistenceUnit(old);
		}		 
	}
		 
	 public void removeAll(Collection<E> element) {
		 IPersistenceUnit old = setPersistenceUnit();
		 try {
			 DefaultCommander.deleteAll(element);
		 } finally {
			 restorePersistenceUnit(old);
		 }		 
	 }
	 
	 public void update(E element) {		 
		 IPersistenceUnit old = setPersistenceUnit();
		 try {
			 DefaultCommander.update(element);
		 } finally {
			 restorePersistenceUnit(old);
		 }
	 }
	 
	 public void updateAll(Collection<E> element) {
		 IPersistenceUnit old = setPersistenceUnit();
		 try {
			 DefaultCommander.updateAll(element);
		 } finally {
			 restorePersistenceUnit(old);
		 }		 
	 }
	 	 
}
