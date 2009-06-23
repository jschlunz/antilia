/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.ConjunctionRestriction;
import com.antilia.common.query.IRestriction;
import com.antilia.common.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class ConjunctionRestrictionTransformer extends FilterToCriterionTransformer {

	private static final long serialVersionUID = 1L;

	public Criterion transform(IRestriction source) {
		if(source instanceof ConjunctionRestriction) {			
			ConjunctionRestriction conjunctionFilter = (ConjunctionRestriction)source;
			Conjunction conjunction =  Restrictions.conjunction();		
			for(IRestriction filter: conjunctionFilter.getFilters()) {
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
