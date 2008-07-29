/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CreatePanelPanelButtonsFactory implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;
	
	private static CreatePanelPanelButtonsFactory instance;

	private CreatePanelPanelButtonsFactory() {
	}
	
	@SuppressWarnings("unchecked")
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {			
		itemHolder.addMenuItem(new SaveNewRecordButton());
		itemHolder.addMenuItem(new CancelButton());		
	}

	/**
	 * @return the instance
	 */
	public static CreatePanelPanelButtonsFactory getInstance() {
		if(instance == null)
			instance = new CreatePanelPanelButtonsFactory();
		return instance;
	}

}
