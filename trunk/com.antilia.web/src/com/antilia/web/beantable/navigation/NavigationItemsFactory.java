/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.button.SeparatorButton;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NavigationItemsFactory implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static NavigationItemsFactory instance;

	private NavigationItemsFactory() {
	}
	
	@SuppressWarnings("unchecked")
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		itemHolder.addMenuItem(new SeparatorButton());
		
		itemHolder.addMenuItem(new FirstPageButton());
		itemHolder.addMenuItem(new PreviousPageButton());
		itemHolder.addMenuItem(new PageNumberItem());
		itemHolder.addMenuItem(new NextPageButton());
		itemHolder.addMenuItem(new LastPageButton());
		
		itemHolder.addMenuItem(new SeparatorButton());		
		itemHolder.addMenuItem(new PageSizeButton());
		itemHolder.addMenuItem(new RefreshButton());
		
		itemHolder.addMenuItem(new ColumnProperties());		
	}

	/**
	 * @return the instance
	 */
	public static NavigationItemsFactory getInstance() {
		if(instance == null)
			instance = new NavigationItemsFactory();
		return instance;
	}

}
