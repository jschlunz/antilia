/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import com.antilia.demo.manager.entities.City;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.layout.BackToHomeCRUD;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CityCRUDPanel extends BackToHomeCRUD<City> {

	private static final long serialVersionUID = 1L;

	private static class CityCRUDStyle extends CrudStyler<City> {
		
		private static final long serialVersionUID = 1L;

		public CityCRUDStyle() {
			super(City.class);
			addEditFields("name", "country");
			addTableColumns("name", "country");
			addHiddenTableColumns("id");
			addSearchFields("id", "name", "country");
		}		
	}
	
	public CityCRUDPanel(String id) {
		super(id, new CityCRUDStyle());
	}
	
	@Override
	protected void configureColumnModel(IColumnModel<City> model) {
		if(model.getPropertyPath().equals("country"))  {
			model.setWidth(300);
			//model.setSortable(false);
		}
	}
}
