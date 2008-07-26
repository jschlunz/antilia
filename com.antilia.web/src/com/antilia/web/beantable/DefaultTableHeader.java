/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.beantable.navigation.NavigationItemsFactory;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultTableHeader<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public DefaultTableHeader(String id, Table<E> table) {
		super(id);
		
		add(newTableMenu("menu", table));
	}
	
	protected Menu newTableMenu(String id, Table<E> table) {
		return Menu.createMenu("menu", 
				table.getBeforeNavigationMenuItemsFactory(), 
				NavigationItemsFactory.getInstance(),
				table.getAfterNavigationMenuItemsFactory());
	}
}
