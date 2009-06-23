package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IRestriction;

public class NotNullRestrictionTransformer extends FilterToCriterionTransformer {

	
	public Criterion transform(IRestriction source) {
		return Restrictions.isNotNull(source.getPropertyName());
	}
	
}
