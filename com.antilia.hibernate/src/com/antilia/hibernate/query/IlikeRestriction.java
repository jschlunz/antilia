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
public class IlikeRestriction extends FilterToCriterionTransfomer implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;
	private final Object value;	
	private MatchMode matchMode;

	protected IlikeRestriction(String propertyName, Object value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	protected IlikeRestriction(String propertyName, String value, MatchMode matchMode) {
		this.propertyName = propertyName;
		this.value = value;
		this.matchMode = matchMode;
	}
	
	
	public String toString() {
		return propertyName + " ilike " + value;
	}
	
	@Override
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	@Override
	public Criterion transform(IFilter source) {
		if(matchMode != null)
			return Restrictions.ilike(propertyName, value.toString(), matchMode.toMatchMode());
		
		return Restrictions.ilike(propertyName, value);
	}
}
