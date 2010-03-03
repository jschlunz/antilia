/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SqlRestriction implements IRestriction {

	private static final long serialVersionUID = 1L;

	private final String sql;

	protected SqlRestriction(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public String getPropertyName() {
		return null;
	}

}
