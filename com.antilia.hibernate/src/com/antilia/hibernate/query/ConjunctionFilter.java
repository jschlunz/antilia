/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ConjunctionFilter extends JunctionFilter {

	private static final long serialVersionUID = 1L;

	/**
	 * @param op
	 */
	public ConjunctionFilter() {
		super(LogicalOperator.AND);
	}

	public Criterion transform(IFilter source) {
		Conjunction conjunction =  Restrictions.conjunction();
		for(IRestrictionFilter filter: getFilters()) {
			//TODO: this is ugly find a better way to do it so that IFilter has no references to hibernate criteria...
			conjunction.add(filter.getTransformer().transform(filter));
		}
		return conjunction;
	}
}
