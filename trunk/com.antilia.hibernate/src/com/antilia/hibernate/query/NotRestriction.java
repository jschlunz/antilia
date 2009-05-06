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
public class NotRestriction extends FilterToCriterionTransfomer implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final IRestrictionFilter filter;
	/**
	 * 
	 */
	public NotRestriction(IRestrictionFilter filter) {
		this.filter = filter;
	}
	/**
	 * @return the filter
	 */
	public IRestrictionFilter getFilter() {
		return filter;
	}
	
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}

	public Criterion transform(IFilter source) {
		// TODO: ugly, fix this...
		return Restrictions.not(getFilter().getTransformer().transform(getFilter()));
	}
	
	public String getPropertyName() {
		if(this.filter != null) {
			return this.filter.getPropertyName();
		}
		return null;
	}
}
