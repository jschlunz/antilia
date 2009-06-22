/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;

import com.antilia.common.query.IQuery;
import com.antilia.hibernate.query.transform.impl.QueryToCriteriaTransformer;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CountCommand<E extends Serializable> extends AbstractPersistentCommand<E, Long> {

	private String hql;
	
	private Criterion[] criteria;
	
	private E sample;
	
	private IQuery<E> query;
	
	private ILoadCommand<E> loadCommand;
	
	@SuppressWarnings("unchecked")
	public CountCommand(E sample) {
		super((Class<E>)sample.getClass());
		this.sample = sample;
	}
	
	public CountCommand(Class<E> persistentClass, String hql) {
		super(persistentClass);
		this.hql = hql;
	}
	
	public CountCommand(Class<E> persistentClass, Criterion... criteria) {
		super(persistentClass);
		this.criteria = criteria;
	}
	
	public CountCommand(IQuery<E> query) {
		super(query.getEntityClass());
		this.query = query;
	}
	
	public CountCommand(ILoadCommand<E> loadCommand) {
		super(null);
		this.loadCommand = loadCommand;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.command.AbstractPersistentCommand#doExecute()
	 */
	@Override
	protected Long doExecute() throws Throwable {
		if(getQuery() != null) {
			Criteria criteria =new QueryToCriteriaTransformer<E>().transform(getQuery(), false);
			criteria.setProjection(Projections.rowCount());
			return new Long(criteria.uniqueResult().toString());
		} else if(getCriteria() != null) {
			Criteria criteria =getSession().createCriteria(getPersistentClass());					
			for(Criterion criterion: getCriteria()) {
				criteria.add(criterion);
			}			
			criteria.setProjection(Projections.rowCount());
			return new Long(criteria.uniqueResult().toString());
		} else if(getHql() != null){
			// TODO: this will not work if the HQL does not contains a proper SQL count command?
			String hql = getHql();
			Query query = getSession().createQuery(hql);
			return (Long)query.uniqueResult();
		} else if(getSample() != null) {
			Criteria criteria =getSession().createCriteria(getPersistentClass());
			criteria.add(Example.create(getSample())).setProjection(Projections.rowCount());
			return new Long(criteria.uniqueResult().toString());
		} else {
			Criteria criteria =getSession().createCriteria(getPersistentClass());		
			return new Long(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
		}
	}

	/**
	 * @return the hql
	 */
	public String getHql() {
		if(getLoadCommand() != null)
			return getLoadCommand().getHql();
		return hql;		
	}

	/**
	 * @param hql the hql to set
	 */
	public void setHql(String hql) {
		this.hql = hql;
	}

	/**
	 * @return the criteria
	 */
	public Criterion[] getCriteria() {
		if(getLoadCommand() != null)
			return getLoadCommand().getCriteria();
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criterion[] criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the sample
	 */
	public E getSample() {
		if(getLoadCommand() != null)
			return getLoadCommand().getSample();
		return sample;
	}

	/**
	 * @param sample the sample to set
	 */
	public void setSample(E sample) {
		this.sample = sample;
	}

	/**
	 * @return the loadCommand
	 */
	public ILoadCommand<E> getLoadCommand() {
		return loadCommand;
	}

	/**
	 * @param loadCommand the loadCommand to set
	 */
	public void setLoadCommand(ILoadCommand<E> loadCommand) {
		this.loadCommand = loadCommand;
	}

	/**
	 * @return the query
	 */
	public IQuery<E> getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(IQuery<E> query) {
		this.query = query;
	}

}
