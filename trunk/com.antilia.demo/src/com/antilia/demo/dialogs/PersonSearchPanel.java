/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import com.antilia.demo.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.navigator.IPageableNavigator;
import com.antilia.web.navigator.impl.InMemoryPageableNavigator;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PersonSearchPanel extends SearchPanel<Person> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public PersonSearchPanel(String id, CrudStyler<Person> styler) {
		super(id, null, new InMemoryPageableNavigator<Person>(DialogsPanel.createPersons(), Person.class), styler);
	}
	
	 @Override
	protected Table<Person> newTable(String id, ITableModel<Person> tableModel, IPageableNavigator<Person> pageableProvider) {
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
}
