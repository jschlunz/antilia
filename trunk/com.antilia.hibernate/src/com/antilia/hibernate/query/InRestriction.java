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
public class InRestriction extends FilterToCriterionTransfomer implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;
	private final Object[] values;

	protected InRestriction(String propertyName, Object[] values) {
		this.propertyName = propertyName;
		this.values = values;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @return the values
	 */
	public Object[] getValues() {
		return values;
	}
	
	@Override
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	@Override
	public Criterion transform(IFilter source) {
		return Restrictions.in(getPropertyName(), getValues());
	}

}
