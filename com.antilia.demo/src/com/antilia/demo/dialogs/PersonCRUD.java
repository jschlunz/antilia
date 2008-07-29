/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import com.antilia.demo.beans.Person;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersonCRUD extends CRUDPanel<Person> {
	
	private static final long serialVersionUID = 1L;

	private static class PersonCrudStyler extends CrudStyler<Person> {
		
		private static final long serialVersionUID = 1L;

		public PersonCrudStyler() {
			super(Person.class);
			addSearchFields("id", "name", "lastName1", "lastName2", "maritalStatus", "birthDay");
			addTableColumns("id", "name", "lastName1", "maritalStatus", "birthDay");
			addEditFields("id", "name", "lastName1", "lastName2", "maritalStatus", "birthDay");
		}		
	}
	/**
	 * @param id
	 */
	public PersonCRUD(String id) {
		super(id, new PersonCrudStyler());
		
	}


	/* (non-Javadoc)
	 * @see com.antilia.web.crud.CRUDPanel#getSearchPanel(java.lang.String)
	 */
	@Override
	protected SearchPanel<Person> getSearchPanel(String id, CrudStyler<Person> styler) {
		return new PersonSearchPanel(id, styler);
	}

}
