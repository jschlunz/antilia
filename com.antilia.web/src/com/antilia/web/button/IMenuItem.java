/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IMenuItem {
	
	String getId();
	
	/**
	 * Returns the position of the button in the menu. Or -1
	 * if order does not matters.
	 * 
	 * @return
	 */
	int getOrder();
}
