package com.antilia.hibernate.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;

public class NotNullRestriction extends FilterToCriterionTransfomer  implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;

	public NotNullRestriction(String propertyName) {
		super();
		this.propertyName = propertyName;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	
	@Override
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
	
	@Override
	public Criterion transform(IFilter source) {
		return Restrictions.isNotNull(getPropertyName());
	}
	
}
