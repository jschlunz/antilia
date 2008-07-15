/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import com.antilia.demo.beans.Person;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.EditPanel;
import com.antilia.web.crud.SearchPanel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersonCRUD extends CRUDPanel<Person> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public PersonCRUD(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.crud.CRUDPanel#getEditPanel(java.lang.String)
	 */
	@Override
	protected EditPanel<Person> getEditPanel(String id) {
		return new EditPanel<Person>(id) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected String[] getEditFields() {
				return new String[]{"id", "name", "lastName1", "lastName2", "maritalStatus", "birthDay"};
			}
		};
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.crud.CRUDPanel#getSearchPanel(java.lang.String)
	 */
	@Override
	protected SearchPanel<Person> getSearchPanel(String id) {
		return new PersonSearchPanel(id);
	}

}
