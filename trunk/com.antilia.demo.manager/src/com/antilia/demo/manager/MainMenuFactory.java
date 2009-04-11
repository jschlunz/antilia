/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import com.antilia.demo.manager.entities.Address;
import com.antilia.demo.manager.entities.City;
import com.antilia.demo.manager.entities.Country;
import com.antilia.demo.manager.entities.Employee;
import com.antilia.web.layout.CRUDButton;
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
		
	/**
	 * 
	 */
	public MainMenuFactory(Index page) {
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.IToolbarItemsFactory#populateMenuItems(java.lang.String, com.antilia.web.toolbar.IToolbar)
	 */
	public void populateMenuItems(String menuId, IToolbar toolbar) {
			SubToolbar subToolbar = new SubToolbar("Entities", toolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Entities";
				}
			};
			toolbar.addItem(subToolbar);
			
			SubToolbar subToolbar2 = new SubToolbar("Addresses", subToolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Addresses";
				}
			};
			
			subToolbar2.addItem(new CRUDButton<City>("Cities", this.page, City.class));
			subToolbar2.addItem(new CRUDButton<Country>("Countries", this.page, Country.class));
			subToolbar2.addItem(new CRUDButton<Address>("Addresses", this.page, Address.class));
			
			
			subToolbar.addItem(subToolbar2);
			
			subToolbar2 = new SubToolbar("other", subToolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Other";
				}
			};			
			subToolbar2.addItem(new CRUDButton<Employee>("Employees", this.page, Employee.class));
			subToolbar.addItem(subToolbar2);
			
	}

}
