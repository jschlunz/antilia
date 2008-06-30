/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import com.antilia.demo.manager.entities.City;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.hibernate.provider.HibernatePageableProvider;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TablePanel extends SearchPanel<City> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablePanel(String id) {
		super(id, City.class);
	}
	
	
	@Override
	protected Table<City> newTable(String id, ITableModel<City> tableModel, IPageableProvider<City> pageableProvider) {
		
		return new Table<City>(id,tableModel, pageableProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void addMenuItemsBeforeNavigation(MenuItemsFactory factory) {
				DialogButton button = new HelloDialogButton("dialogButton");				
				factory.addItem(button);
			}
		};
	}
	
	@Override
	protected IPageableProvider<City> getTableData() {
		Query<City> query = (Query<City>)getFilterQuery();
		HibernatePageableProvider<City> hibernatePageableProvider = new HibernatePageableProvider<City>(query);
		return hibernatePageableProvider;
	}
	
	@Override
	protected String[] getSearchFields() {
		return new String[]{"id", "name", "country"};
	}
	
	@Override
	protected String[] getTableColumns() {
		return new String[]{"id", "name", "country"};
	}
}
