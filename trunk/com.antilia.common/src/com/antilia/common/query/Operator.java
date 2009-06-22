/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public enum Operator {
	
	EQUAL("="), 
	NOTEQUAL("<>"),
	LIKE("like "), 
	ILIKE("like "),
	GREATER_THAN(">"),
	LESS_THAN("<"),
	LESS_EQUAL_THAN("<="),
	GREATER_EQUAL_THAN(">="),
	BETWEEN("obj1 < x < obj2"),
	IN("in"),
	ISNULL("isnull"),
	NOTNULL("NOTNULL"),
	EMPTY("EMPTY"),	
	; 
		
	private final String value;
	
	Operator(String value) {
		this.value =value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
