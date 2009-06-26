/**
 * 
 */
package com.antilia.web.provider;

import java.io.Serializable;

import com.antilia.hibernate.dao.impl.IHibernateDao;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class HibernateEntityModel<E extends Serializable> extends AbstractEntityModel<E> {

	private static final long serialVersionUID = 1L;
	
	private IHibernateDao<E> dao;
	
	/**
	 * 
	 * @param entity
	 * @param dao
	 */
	public HibernateEntityModel(E entity, IHibernateDao<E> dao) {
		super(entity);
		this.dao = dao;
	}
	
	
	protected Serializable getId(E entity) {
		if(this.dao != null)
			return this.dao.getKey(entity);
		return null;
	};
	
	@Override
	protected E load(Class<E> clazz, Serializable id) {
		return this.dao.findById(clazz, id);
	}
	
}
