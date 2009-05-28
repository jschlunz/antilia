/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import java.io.Serializable;

import com.antilia.demo.manager.components.progress.ProgressShowPanel;
import com.antilia.demo.manager.components.tables.TablesPanel;
import com.antilia.demo.manager.components.workspace.WorkSpacePanel;
import com.antilia.demo.manager.entities.Address;
import com.antilia.demo.manager.entities.City;
import com.antilia.demo.manager.entities.Country;
import com.antilia.demo.manager.entities.Employee;
import com.antilia.web.layout.CRUDLink;
import com.antilia.web.layout.TopMenuPanel;
import com.antilia.web.layout.TopMenuPanelLink;
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
			
			subToolbar2.addItem(new CRUDLink<City>("Cities",  City.class));
			subToolbar2.addItem(new CRUDLink<Country>("Countries", Country.class));
			subToolbar2.addItem(new CRUDLink<Address>("Addresses", Address.class));
			
			
			subToolbar.addItem(subToolbar2);
			
			subToolbar2 = new SubToolbar("other", subToolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Other";
				}
			};			
			subToolbar2.addItem(new CRUDLink<Employee>("Employees", Employee.class));
			subToolbar.addItem(subToolbar2);
			
			subToolbar = new SubToolbar("Components", toolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Components";
				}
			};
			
			toolbar.addItem(subToolbar);
			
			subToolbar.addItem(new TopMenuPanelLink<Serializable>("Tables", this.page) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected TopMenuPanel<Serializable> createTopMenuPanel(String id) {					
					return new TablesPanel(id);
				}
			});
			
			subToolbar.addItem(new TopMenuPanelLink<Serializable>("Workspace", this.page) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected TopMenuPanel<Serializable> createTopMenuPanel(String id) {					
					return new WorkSpacePanel(id);
				}
			});
			
			subToolbar.addItem(new TopMenuPanelLink<Serializable>("Progress Bar", this.page) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected TopMenuPanel<Serializable> createTopMenuPanel(String id) {					
					return new ProgressShowPanel(id);
				}
			});
			
	}

}
