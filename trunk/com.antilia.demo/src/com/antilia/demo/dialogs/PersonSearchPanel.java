/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import com.antilia.demo.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.impl.InMemoryPageableProvider;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.dialog.DialogButton;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PersonSearchPanel extends SearchPanel<Person> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public PersonSearchPanel(String id) {
		super(id, Person.class);
	}
	
	 @Override
	protected Table<Person> newTable(String id, ITableModel<Person> tableModel, IPageableProvider<Person> pageableProvider) {
		 return new Table<Person>(id,tableModel, pageableProvider) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void addMenuItemsBeforeNavigation(MenuItemsFactory factory) {
					DialogButton button = new HelloDialogButton("dialogButton");				
					factory.addItem(button);
					TestLoadingButton  testLoadingButton = new TestLoadingButton() ;
					factory.addItem(testLoadingButton);				
				}
			};
	}
		
	
	@Override
	protected IPageableProvider<Person> getTableData() {
		return new InMemoryPageableProvider<Person>(DialogsPanel.createPersons());
	}
	
	@Override
	protected String[] getSearchFields() {
		return new String[]{"id", "name", "lastName1", "lastName2", "maritalStatus"};
	}
	
	@Override
	protected String[] getTableColumns() {
		return new String[]{"id", "name", "lastName1", "maritalStatus"};
	}
}
