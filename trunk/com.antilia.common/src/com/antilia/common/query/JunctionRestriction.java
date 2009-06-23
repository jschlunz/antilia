/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for junction restrictions.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class JunctionRestriction  implements IRestriction {

	private static final long serialVersionUID = 1L;
	
	private final List<IRestriction> filters = new ArrayList<IRestriction>();
	private final LogicalOperator op;
	
	protected JunctionRestriction(LogicalOperator op) {
		this.op = op;
	}
	
	public JunctionRestriction add(IRestriction filter) {
		filters.add(filter);
		return this;
	}
	
	public Iterable<IRestriction> getFilters() {
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
