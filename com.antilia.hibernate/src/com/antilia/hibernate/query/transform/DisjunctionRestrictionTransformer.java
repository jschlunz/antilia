/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.DisjunctionRestriction;
import com.antilia.common.query.IRestriction;
import com.antilia.common.query.transform.IRestrictionTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class DisjunctionRestrictionTransformer extends FilterToCriterionTransformer {

	private static final long serialVersionUID = 1L;

	public Criterion transform(IRestriction source) {
		if(source instanceof DisjunctionRestriction) {
			DisjunctionRestriction disjunctionFilter = (DisjunctionRestriction)source;
			Disjunction conjunction =  Restrictions.disjunction();		
			for(IRestriction filter: disjunctionFilter.getFilters()) {
				IRestrictionTransformer<Criterion>  transformer = HibernateTransformerLocator.getInstance().getTransformer(filter);				
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
