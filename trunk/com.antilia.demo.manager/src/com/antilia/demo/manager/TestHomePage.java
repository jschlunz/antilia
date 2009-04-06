/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager;

import com.antilia.demo.manager.entities.City;
import com.antilia.web.crud.AutoCrudStyler;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.layout.FullPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestHomePage extends FullPage {

	/**
	 * 
	 */
	public TestHomePage() {
		
		add(new CRUDPanel<City>("table",new AutoCrudStyler<City>(City.class)));
	}
}
