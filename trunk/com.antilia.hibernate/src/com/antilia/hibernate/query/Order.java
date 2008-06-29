/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

import java.io.Serializable;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Order<B extends Serializable> implements IOrder {

	private static final long serialVersionUID = 1L;	
	
	private String propertyPath;
	private OrderType type;
	private boolean ignoreCase;	
	
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
	public static <T extends Serializable> Order<T> asc(String propertyPath) {
		return new Order<T>(propertyPath, OrderType.ASCENDING);
	}
	
	/**
	 * Creates a descending order for  propertyPath.
	 * @param <T>
	 * @param propertyPath
	 * @return
	 */
	public static <T extends Serializable> Order<T> des(String propertyPath) {
		return new Order<T>(propertyPath, OrderType.DESCENDING);
	}
	
	/**
	 * @return the type
	 */
	protected OrderType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	protected Order<B> setType(OrderType type) {
		this.type = type;
		return this;
	}

	/**
	 * @return the ignoreCase
	 */
	protected boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * @param ignoreCase the ignoreCase to set
	 */
	protected Order<B> ignoreCase(boolean ignoreCase) {
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
	public Order<B> setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
		return this;
	}

}
