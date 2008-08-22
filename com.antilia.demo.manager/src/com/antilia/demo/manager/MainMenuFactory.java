/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import com.antilia.demo.manager.crud.CityModalContainer;
import com.antilia.demo.manager.crud.CountryModalContainer;
import com.antilia.web.toolbar.IToolbar;
import com.antilia.web.toolbar.IToolbarItemsFactory;
import com.antilia.web.toolbar.SubToolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MainMenuFactory implements IToolbarItemsFactory {

	private static final long serialVersionUID = 1L;

	private Index page;
	
	private String contentId;
	/**
	 * 
	 */
	public MainMenuFactory(Index page, String contentId) {
		this.page = page;
		this.contentId = contentId;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.IToolbarItemsFactory#populateMenuItems(java.lang.String, com.antilia.web.toolbar.IToolbar)
	 */
	@Override
	public void populateMenuItems(String menuId, IToolbar toolbar) {
			SubToolbar subToolbar = new SubToolbar("cities", toolbar) {
				@Override
				protected String getTitle() {
					return "Cities";
				}
			};
			toolbar.addItem(subToolbar);
			
			subToolbar.addItem(new CRUDButton("city", "Cities", this.page, new CityModalContainer(this.contentId)));
			subToolbar.addItem(new CRUDButton("county", "Counties", this.page, new CountryModalContainer(this.contentId)));
	}

}
