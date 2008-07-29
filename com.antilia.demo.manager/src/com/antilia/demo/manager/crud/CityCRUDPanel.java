/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import com.antilia.demo.manager.entities.City;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.CrudStyler;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CityCRUDPanel extends CRUDPanel<City> {

	private static final long serialVersionUID = 1L;

	private static class CityCRUDStyle extends CrudStyler<City> {
		
		private static final long serialVersionUID = 1L;

		public CityCRUDStyle() {
			super(City.class);
			addEditFields("name", "country");
			addTableColumns("id", "name", "country");
			addSearchFields("id", "name", "country");
		}		
	}
	public CityCRUDPanel(String id) {
		super(id, new CityCRUDStyle());
	}
	
}
