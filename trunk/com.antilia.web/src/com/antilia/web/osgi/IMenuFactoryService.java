/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.osgi;

import com.antilia.common.osgi.IAggregator;
import com.antilia.web.button.IMenuFactoryPopulator;
import com.antilia.web.button.MenuItemsFactory;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IMenuFactoryService extends IAggregator<IMenuFactoryPopulator> {
	
	MenuFactoryService populateFactory(String factoryId, MenuItemsFactory factory);	
	
}
