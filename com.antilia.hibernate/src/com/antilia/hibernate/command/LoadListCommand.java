/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.transform.QueryToCriteriaTransformer;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LoadListCommand<E extends Serializable> extends AbstractPersistentCommand<E, List<E>> implements ILoadCommand<E> {
	
	private String hql;
	
	private Criterion[] criteria;
	
	private E sample;
	
	private IQuery<E> query;
	
	@SuppressWarnings("unchecked")
	public LoadListCommand(E sample) {
		super((Class<E>)sample.getClass());
		this.sample = sample;
	}
	
	public LoadListCommand(IQuery<E> query) {
		super(query.getEntityClass());
		this.query = query;
	}
	
	public LoadListCommand(Class<E> persistentClass, String hql) {
		super(persistentClass);
		this.hql = hql;
	}
	
	public LoadListCommand(Class<E> persistentClass, Criterion... criteria) {
		super(persistentClass);
		this.criteria = criteria;
	}	

	public static <T extends Serializable> List<T> findlAll(Class<T> persistentClass) {
		try {
			return new LoadListCommand<T>(persistentClass).execute();
		} catch (Throwable e) {
			throw new CommandExecutionException(CommandExecutionException.COMMAND_EXECUTION_EXCEPTION, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<E> doExecute() {
		if(getQuery() != null) {
			Criteria criteria =new QueryToCriteriaTransformer<E>().transform(getQuery(), true);
			return (List<E>)criteria.list();
		} else if(getCriteria() != null) {
			Criteria criteria =getSession().createCriteria(getPersistentClass());		
			for(Criterion criterion: getCriteria()) {
				criteria.add(criterion);
			}
			return (List<E>)criteria.list();
		} else if(getHql() != null){
			String hql = getHql();
			Query query = getSession().createQuery(hql);
			return (List<E>)query.list();
		} else if(getSample() != null) {
			Criteria criteria =getSession().createCriteria(getPersistentClass());
			return (List<E>)criteria.add(Example.create(getSample())).list();
		} else {
			Criteria criteria =getSession().createCriteria(getPersistentClass());		
			return (List<E>)criteria.list();
		}
	}


	/* (non-Javadoc)
	 * @see com.antilia.hibernate.command.ILoadCommand#getHql()
	 */
	public String getHql() {
		return hql;
	}


	/**
	 * @param hql the hql to set
	 */
	public void setHql(String hql) {
		this.hql = hql;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.command.ILoadCommand#getCriteria()
	 */
	public Criterion[] getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criterion[] criteria) {
		this.criteria = criteria;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.command.ILoadCommand#getSample()
	 */
	public E getSample() {
		return sample;
	}

	/**
	 * @param sample the sample to set
	 */
	public void setSample(E sample) {
		this.sample = sample;
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
