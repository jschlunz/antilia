/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.query;

import java.io.Serializable;


/**
 * Represents a restriction in a query.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IRestriction extends Serializable {

	/**
	 *  
	 * @return The property to which the filter will apply (or null if the property does not applies to a given filter).
	 */
	String getPropertyName();
}
