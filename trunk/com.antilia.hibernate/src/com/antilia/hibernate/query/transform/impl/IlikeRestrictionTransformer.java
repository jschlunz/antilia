/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.IlikeRestriction;
import com.antilia.common.query.MatchMode;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class IlikeRestrictionTransformer extends FilterToCriterionTransformer {


	
	public Criterion transform(IRestriction source) {
		if(source instanceof IlikeRestriction) {
			IlikeRestriction restriction = (IlikeRestriction)source;
			if(restriction.getMatchMode() != null)
				return Restrictions.ilike(restriction.getPropertyName(), restriction.getValue().toString(), toMatchMode(restriction.getMatchMode()));
			//TODO: restriction.getValue().toString()?
			return Restrictions.ilike(restriction.getPropertyName(), restriction.getValue());
		}
		return null;
	}

	public org.hibernate.criterion.MatchMode toMatchMode(MatchMode matchMode) {
		if(matchMode.equals(MatchMode.EXACT)){
			return org.hibernate.criterion.MatchMode.EXACT;
		}
		if(matchMode.equals(MatchMode.ANYWHERE)){
			return org.hibernate.criterion.MatchMode.ANYWHERE;
		}
		if(matchMode.equals(MatchMode.END)){
			return org.hibernate.criterion.MatchMode.END;
		}
		if(matchMode.equals(MatchMode.START)){
			return org.hibernate.criterion.MatchMode.START;
		}
		return org.hibernate.criterion.MatchMode.START;
	}
}
