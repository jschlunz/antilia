/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IFilter;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NotEmptyRestrictionTransformer extends FilterToCriterionTransformer {
	
	public Criterion transform(IFilter source) {
		return Restrictions.isNotEmpty(source.getPropertyName());
	}
}
