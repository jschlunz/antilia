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
public interface IOrder extends Serializable {
	public static enum  OrderType {
		ASCENDING,
		DESCENDING
	};
}
