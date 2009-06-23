/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.io.Serializable;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Order<B extends Serializable> implements IOrder<B> {

	private static final long serialVersionUID = 1L;	
	
	String propertyPath;
	OrderType type;
	boolean ignoreCase;	
	
	public Order() {
		super();
	}
	
	/**
	 *Constructor. 
	 */
	public Order(String propertyPath, OrderType type) {
		this.propertyPath = propertyPath;
		this.type = type;
	}

	/**
	 * Creates an ascending order for  propertyPath.
	 * @param <T>
	 * @param propertyPath
	 * @return
	 */
	public static <T extends Serializable> IOrder<T> asc(String propertyPath) {
		return new Order<T>(propertyPath, OrderType.ASCENDING);
	}
	
	/**
	 * Creates a descending order for  propertyPath.
	 * @param <T>
	 * @param propertyPath
	 * @return
	 */
	public static <T extends Serializable> IOrder<T> des(String propertyPath) {
		return new Order<T>(propertyPath, OrderType.DESCENDING);
	}
	
	/**
	 * @return the type
	 */
	public OrderType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public IOrder<B> setType(OrderType type) {
		this.type = type;
		return this;
	}

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * @param ignoreCase the ignoreCase to set
	 */
	public IOrder<B> ignoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
		return this;
	}

	/**
	 * @return the propertyPath
	 */
	public String getPropertyPath() {
		return propertyPath;
	}

	/**
	 * @param propertyPath the propertyPath to set
	 */
	public IOrder<B> setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
		return this;
	}

}
