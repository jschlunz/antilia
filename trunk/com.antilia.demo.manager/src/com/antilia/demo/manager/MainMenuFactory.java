/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import com.antilia.demo.manager.entities.Address;
import com.antilia.demo.manager.entities.City;
import com.antilia.demo.manager.entities.Country;
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
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Cities";
				}
			};
			toolbar.addItem(subToolbar);
			
			subToolbar.addItem(new CRUDButton<City>("Cities", this.page, City.class, this.contentId));
			subToolbar.addItem(new CRUDButton<Country>("Countries", this.page, Country.class, this.contentId));
			
			subToolbar = new SubToolbar("other", toolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Other";
				}
			};
			toolbar.addItem(subToolbar);
			subToolbar.addItem(new CRUDButton<Address>("Addresses", this.page, Address.class,this.contentId));
	}

}
