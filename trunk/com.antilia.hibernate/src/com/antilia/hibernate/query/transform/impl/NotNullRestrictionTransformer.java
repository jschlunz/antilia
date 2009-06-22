package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.common.query.IFilter;

public class NotNullRestrictionTransformer extends FilterToCriterionTransformer {

	
	public Criterion transform(IFilter source) {
		return Restrictions.isNotNull(source.getPropertyName());
	}
	
}
