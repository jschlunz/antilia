/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.toolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IToolbar {

	/**
	 * If the toolbar is a top level one.
	 * 
	 * @return
	 */
	boolean isOnTop();
	
	
	public IToolbar addItem(IToolbarItem item);
	
	
	public IToolbar removeItem(IToolbarItem item);
}
