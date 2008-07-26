/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu.osgi;

import java.util.Iterator;

import com.antilia.web.button.IMenuItemsFactory;

/**
 *  Interface defining a service that allows to register IMenuItemsFactory(ies).
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IMenuItemsFactoryService {
	
	/**
	 * 
	 * @return returns the registered menu items factories...
	 */
	Iterator<IMenuItemsFactory> getMenuItemsFactories();

	/**
	 * Adds (registers) an IMenuItemsFactory.
	 * 
	 * @param factory
	 */
	void addMenuItemsFactory( IMenuItemsFactory  factory);
	
	/**
	 * Removes (deregisters) an IMenuItemsFactory.
	 * 
	 * @param factory
	 */
	void removeMenuItemsFactory(IMenuItemsFactory  factory);
	
}
