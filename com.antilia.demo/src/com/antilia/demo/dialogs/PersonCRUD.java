/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import com.antilia.demo.beans.Person;
import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.provider.IQuerableDataProvider;
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
	protected SearchPanel<Person> newSearchPanel(String id, CrudStyler<Person> styler) {
		return new PersonSearchPanel(id, styler) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void configureColumnModel(IColumnModel<Person> model) {
				PersonCRUD.this.configureColumnModel(model);
			}
			
						
			@Override
			protected IQuerableDataProvider<Person> createPageableProvider(IQuery<Person> filterQuery) {
				return PersonCRUD.this.createPageableProvider(filterQuery);
			}
		};
	}

}
