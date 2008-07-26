/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common;

import org.slf4j.Logger;

/**
 * Marks a class as loggable.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ILoggable {
	
	/**
	 * @return Returns the logger of the class.
	 */
	Logger getLogger(); 
	
}
