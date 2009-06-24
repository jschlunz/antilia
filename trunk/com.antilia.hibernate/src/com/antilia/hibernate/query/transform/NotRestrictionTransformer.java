/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.NotRestriction;
import com.antilia.common.query.transform.IRestrictionTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NotRestrictionTransformer extends FilterToCriterionTransformer {


	public Criterion transform(IRestriction source) {
		if(source instanceof NotRestriction) {
			IRestrictionTransformer<Criterion> transformer = HibernateTransformerLocator.getInstance().getTransformer(source);
			if(transformer != null) {
				transformer.transform(source);
			}
		}
		return null;
	}
}
