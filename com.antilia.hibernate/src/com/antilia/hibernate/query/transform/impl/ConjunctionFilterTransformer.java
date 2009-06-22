/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.ConjunctionFilter;
import com.antilia.common.query.IFilter;
import com.antilia.common.query.IRestrictionFilter;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class ConjunctionFilterTransformer extends FilterToCriterionTransformer {

	private static final long serialVersionUID = 1L;

	public Criterion transform(IFilter source) {
		if(source instanceof ConjunctionFilter) {			
			ConjunctionFilter conjunctionFilter = (ConjunctionFilter)source;
			Conjunction conjunction =  Restrictions.conjunction();		
			for(IRestrictionFilter filter: conjunctionFilter.getFilters()) {
				IFilterTransformer<Criterion>  transformer = HibernateTransformerLocator.getInstance().getTransformer(filter);				
				if(transformer != null)
					conjunction.add(transformer.transform(filter));
			}			
			return conjunction;
		}
		return null;
	}
	
	public String getPropertyName() {
		return null;
	}
}
