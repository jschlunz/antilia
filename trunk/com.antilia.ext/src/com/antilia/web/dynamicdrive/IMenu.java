/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dynamicdrive;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IMenu {

	/**
	 * If the toolbar is a top level one.
	 * 
	 * @return
	 */
	boolean isOnTop();
	
	
	public IMenu addItem(IMenuItem item);
	
	
	public IMenu removeItem(IMenuItem item);
}
