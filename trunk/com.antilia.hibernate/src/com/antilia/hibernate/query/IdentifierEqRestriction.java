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
public class IdentifierEqRestriction extends FilterToCriterionTransfomer  implements IRestrictionFilter{

	private static final long serialVersionUID = 1L;

	private final Object value;

	protected IdentifierEqRestriction(Object value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	public Criterion transform(IFilter source) {
		return Restrictions.idEq(value);
	}
}
