/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class MenuItemsFactory implements IMenuItemsFactory {

	private static final long serialVersionUID = 1L;
	
	private List<IMenuItem> items = new ArrayList<IMenuItem>();
	
	private String id;
	
	public MenuItemsFactory(String id){		
		this.id = id;
	}
	
	public MenuItemsFactory(IMenuItem... items) {
		addItems(items);
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		for (IMenuItem item: items) {
			itemHolder.addMenuItem(item);
		}
	}
	
	public MenuItemsFactory addItems(IMenuItem... items) {
		if(items != null)
			for(IMenuItem item: items)  {
				addItem(item);
			}
		return this;
	}
	
	public MenuItemsFactory addItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	public MenuItemsFactory removeAll() {
		items.clear();
		return this;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
