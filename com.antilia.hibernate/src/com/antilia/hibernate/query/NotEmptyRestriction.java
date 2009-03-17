/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NotEmptyRestriction extends FilterToCriterionTransfomer  implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final String propertyName;
	
	/**
	 * 
	 */
	public NotEmptyRestriction(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	public Criterion transform(IFilter source) {
		return org.hibernate.criterion.Restrictions.isNotEmpty(getPropertyName());
	}
}
