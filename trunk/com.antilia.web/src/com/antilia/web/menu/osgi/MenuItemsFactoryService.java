/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu.osgi;

import java.util.ArrayList;
import java.util.Iterator;

import com.antilia.web.button.IMenuItemsFactory;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MenuItemsFactoryService implements IMenuItemsFactoryService {

	private java.util.List<IMenuItemsFactory> factories = new ArrayList<IMenuItemsFactory>();
	
	private static MenuItemsFactoryService instance;
	
	/**
	 * 
	 */
	private MenuItemsFactoryService() {
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.menu.osgi.IMenuItemFactoryService#addMenuItemsFactory(com.antilia.web.button.IMenuItemsFactory)
	 */
	public void addMenuItemsFactory(IMenuItemsFactory factory) {
		factories.remove(factory);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.menu.osgi.IMenuItemFactoryService#getMenuItemsFactories()
	 */
	public Iterator<IMenuItemsFactory> getMenuItemsFactories() {
		return factories.iterator();
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.menu.osgi.IMenuItemFactoryService#removeMenuItemsFactory(com.antilia.web.button.IMenuItemsFactory)
	 */
	public void removeMenuItemsFactory(IMenuItemsFactory factory) {
		factories.add(factory);
	}

	public static MenuItemsFactoryService getInstance() {
		if(instance == null) {
			instance = new MenuItemsFactoryService();
		}
		return instance;
	}

}
