/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.osgi;

import com.antilia.common.osgi.Aggregator;
import com.antilia.web.button.IMenuFactoryPopulator;
import com.antilia.web.button.MenuItemsFactory;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MenuFactoryService extends Aggregator<IMenuFactoryPopulator> implements IMenuFactoryService {
	private static final long serialVersionUID = 1L;
	
	private static final MenuFactoryService instance = new MenuFactoryService();
	
	/**
	 * 
	 */
	private  MenuFactoryService() {
	}

	public MenuFactoryService populateFactory(String factoryId, MenuItemsFactory factory) {		
		if(factoryId.equals(factory.getId())) {
			for(IMenuFactoryPopulator item : elements()) {
				item .populateMenuFactory(factory);
			}
		}
		return this;
	}	
	

	/**
	 * @return the instance
	 */
	public static MenuFactoryService getInstance() {
		return instance;
	}
	

}
