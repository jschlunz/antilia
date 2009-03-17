/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class BetweenRestriction extends FilterToCriterionTransfomer implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	private final Object lo;
	private final Object hi;
	
	protected BetweenRestriction(String propertyName, Object lo, Object hi) {
		this.propertyName = propertyName;
		this.lo = lo;
		this.hi = hi;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the lo
	 */
	public Object getLo() {
		return lo;
	}

	/**
	 * @return the hi
	 */
	public Object getHi() {
		return hi;
	}
	
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	public Criterion transform(IFilter source) {
		return Restrictions.between(propertyName, lo, hi);
	}
}
