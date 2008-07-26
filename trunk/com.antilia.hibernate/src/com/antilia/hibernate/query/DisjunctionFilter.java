/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class DisjunctionFilter extends JunctionFilter {

	private static final long serialVersionUID = 1L;

	/**
	 * @param op
	 */
	public DisjunctionFilter() {
		super(LogicalOperator.OR);
	}

	public Criterion transform(IFilter source) {
		Disjunction conjunction =  Restrictions.disjunction();
		for(IRestrictionFilter filter: getFilters()) {
			//TODO: this is ugly find a better way to do it so that IFilter has no references to hibernate criteria...
			conjunction.add(filter.getTransformer().transform(filter));
		}
		return conjunction;
	}
}
