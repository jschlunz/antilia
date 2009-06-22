/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class JunctionFilter  implements IRestrictionFilter {

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
	
	public Iterable<IRestrictionFilter> getFilters() {
		return filters;
	}

	/**
	 * @return the op
	 */
	public LogicalOperator getOp() {
		return op;
	}
	
	public String getPropertyName() {
		return null;
	}
}
