/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ColumnMenuItemsFactory implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static ColumnMenuItemsFactory instance;

	private ColumnMenuItemsFactory() {
	}
	
	@SuppressWarnings("unchecked")
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		itemHolder.addMenuItem(new ColumnProperties());				
	}

	/**
	 * @return the instance
	 */
	public static ColumnMenuItemsFactory getInstance() {
		if(instance == null)
			instance = new ColumnMenuItemsFactory();
		return instance;
	}

}
