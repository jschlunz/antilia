/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.buttons;

import com.antilia.demo.Index;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.menu.DropDownButton;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class MainButtonsFactory implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;

	private Index page;
	public MainButtonsFactory(Index page) {
		this.page=page;
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		DropDownButton dropDownButton = DropDownButton.createDropDownButton("otherMenu", "Other", null, new IMenuItem[]{
				new EffectsPageButton(page), 
				new RoundBoxButton(page),
				new DialogsPageButton(page)});
		itemHolder.addMenuItem(dropDownButton);
		
		dropDownButton = DropDownButton.createDropDownButton("tablesMenu", "Tables", null, 
				new IMenuItem[]{
				new TablesButton(page),
				new NestedTablesButton(page)
				});		
		itemHolder.addMenuItem(dropDownButton);
	}
}
