/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import com.antilia.demo.manager.entities.Country;
import com.antilia.web.crud.CrudStyler;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CountryCRUDPanel extends ManagerCRUD<Country> {

	private static final long serialVersionUID = 1L;

	public CountryCRUDPanel(String id) {
		super(id, 
				new CrudStyler<Country>(Country.class).
				addEditFields("name", "domain").
				addTableColumns("id", "name", "domain").
				addSearchFields("id", "name", "domain")
		);
	}
	
}
