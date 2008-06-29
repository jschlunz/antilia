/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.antilia.hibernate.query.transform.FilterToCriterionTransfomer;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class JunctionFilter extends FilterToCriterionTransfomer implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;
	
	private final List<IRestrictionFilter> filters = new ArrayList<IRestrictionFilter>();
	private final LogicalOperator op;
	
	protected JunctionFilter(LogicalOperator op) {
		this.op = op;
	}
	
	public JunctionFilter add(IRestrictionFilter filter) {
		filters.add(filter);
		return this;
	}
	
	protected Iterable<IRestrictionFilter> getFilters() {
		return filters;
	}

	/**
	 * @return the op
	 */
	public LogicalOperator getOp() {
		return op;
	}
	
	@Override
	public IFilterTransformer<Criterion> getTransformer() {
		return this;
	}
}
