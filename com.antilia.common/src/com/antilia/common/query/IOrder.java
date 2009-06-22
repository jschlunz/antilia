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
public interface IOrder<B extends Serializable> extends Serializable {
	public static enum  OrderType {
		ASCENDING,
		DESCENDING,
		NONE
	}

	/**
	 * @return the type
	 */
	public OrderType getType();

	/**
	 * @param type the type to set
	 */
	public Order<B> setType(OrderType type);

	/**
	 * @return the ignoreCase
	 */
	public boolean isIgnoreCase();

	/**
	 * @param ignoreCase the ignoreCase to set
	 */
	public Order<B> ignoreCase(boolean ignoreCase);

	/**
	 * @return the propertyPath
	 */
	public String getPropertyPath();

	/**
	 * @param propertyPath the propertyPath to set
	 */
	public Order<B> setPropertyPath(String propertyPath);;
}
