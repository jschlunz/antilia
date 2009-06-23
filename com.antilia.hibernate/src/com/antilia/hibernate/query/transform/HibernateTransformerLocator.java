/**
 * 
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.transform.IFilterTransformer;
import com.antilia.hibernate.PersistenceException;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class HibernateTransformerLocator implements ITransformerLocator {

	public static HibernateTransformerLocator instance;
	
	private HibernateTransformerLocator() {
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.transform.ITransformerLocator#getTransformer(com.antilia.hibernate.query.IFilter)
	 */
	@SuppressWarnings("unchecked")
	public IFilterTransformer<Criterion> getTransformer(IRestriction filter) {
		String className = "com.antilia.hibernate.query.transform.impl." + filter.getClass().getSimpleName() + "Transformer";
		try {
			Class<IFilterTransformer<Criterion>> clazz = (Class<IFilterTransformer<Criterion>>)Thread.currentThread().getContextClassLoader().loadClass(className);
			IFilterTransformer<Criterion> transformer = clazz.newInstance();
			return transformer;
		} catch (ClassNotFoundException e) {
			throw new PersistenceException("XXX", e);
		} catch (InstantiationException e) {
			throw new PersistenceException("XXX", e);
		} catch (IllegalAccessException e) {
			throw new PersistenceException("XXX", e);
		}
	}

	/**
	 * @return the instance
	 */
	public static HibernateTransformerLocator getInstance() {
		if(instance == null) {
			instance = new HibernateTransformerLocator();
		}
		return instance;
	}
		

}
