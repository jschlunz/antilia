/**
 * 
 */
package com.antilia.ibatis.dao;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.antilia.common.dao.IQuerableUpdatableDao;

/**
 * A QuerableUpdatable DAO that uses Spring IBatis support.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SpringIBatisQuerableUpdatableDao<E extends Serializable> extends SpringIBatisQuerableDao<E> implements IQuerableUpdatableDao<E> {

	private static final long serialVersionUID = 1L;
	
	
	public SpringIBatisQuerableUpdatableDao() {
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public E add(E element) {	
		String id = "insert"+element.getClass().getSimpleName();
		getSqlMapClientTemplate().insert(id, element);
		return element;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addAll(Collection<E> elements) {
		for(E element: elements) {
			add(element);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public E remove(E element) {
		String id = "delete"+element.getClass().getSimpleName();
		getSqlMapClientTemplate().delete(id, element);
		return element;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeAll(Collection<E> elements) {
		for(E element: elements) {
			remove(element);
		}
	}
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(E element) {
		String id = "update"+element.getClass().getSimpleName();
		getSqlMapClientTemplate().update(id, element);
	}
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateAll(Collection<E> elements) {
		for(E element: elements) {
			update(element);
		}
	}

}
