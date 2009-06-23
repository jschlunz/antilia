/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.LogicalOperator;
import com.antilia.common.query.LogicalRestriction;
import com.antilia.hibernate.query.transform.IFilterTransformer;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
class LogicalRestrictionTransformer extends FilterToCriterionTransformer {
	
	public Criterion transform(IRestriction source) {
		if(source instanceof LogicalRestriction) {
			LogicalRestriction logicalRestriction = (LogicalRestriction)source;
			IRestriction lhs = logicalRestriction.getLhs();
			IFilterTransformer<Criterion> lhst = HibernateTransformerLocator.getInstance().getTransformer(lhs);
			IRestriction rhs = logicalRestriction.getRhs();
			IFilterTransformer<Criterion> rhst = HibernateTransformerLocator.getInstance().getTransformer(rhs);
			if(logicalRestriction.getOp().equals(LogicalOperator.OR)) {								
				return Restrictions.or(lhst.transform(lhs), rhst.transform(rhs));
			} else if(logicalRestriction.getOp().equals(LogicalOperator.AND)) {
				return Restrictions.and(lhst.transform(lhs), rhst.transform(rhs));
			}
		}				
		return null;		
	}

}
