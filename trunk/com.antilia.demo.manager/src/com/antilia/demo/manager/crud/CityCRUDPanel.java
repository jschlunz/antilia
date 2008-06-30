/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import com.antilia.demo.manager.entities.City;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.EditPanel;
import com.antilia.web.crud.SearchPanel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CityCRUDPanel extends CRUDPanel<City> {

	private static final long serialVersionUID = 1L;

	public CityCRUDPanel(String id) {
		super(id);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.crud.CRUDPanel#getSearchPanel(java.lang.String)
	 */
	@Override
	protected SearchPanel<City> getSearchPanel(String id) {
		return new CitySearchPanel(id);
	}

	@Override
	protected EditPanel<City> getEditPanel(String id) {
		return new EditPanel<City>(id, City.class) {
						
			private static final long serialVersionUID = 1L;

			@Override
			protected String[] getSearchFields() {
				return new String[]{"id", "name", "country"};
			}
			
		};
	}
}
