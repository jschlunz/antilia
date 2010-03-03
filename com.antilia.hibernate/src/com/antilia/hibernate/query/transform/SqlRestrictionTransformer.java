/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.SqlRestriction;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class SqlRestrictionTransformer extends RestrictionToCriterionTransformer {

	private static final long serialVersionUID = 1L;

	public Criterion transform(IRestriction source) {
		if(source instanceof SqlRestriction) {			
			SqlRestriction sqlRestriction = (SqlRestriction)source;
			return Restrictions.sqlRestriction(sqlRestriction.getSql());
		}
		return null;
	}
	
	public String getPropertyName() {
		return null;
	}
}
