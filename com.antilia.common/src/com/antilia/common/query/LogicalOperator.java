/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public enum LogicalOperator {

	AND("and"),
	OR("or"),
	NOT("not");

	private final String value;
	
	LogicalOperator(String value) {
		this.value =value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
