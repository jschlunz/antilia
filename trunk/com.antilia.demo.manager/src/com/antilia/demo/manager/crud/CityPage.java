package com.antilia.demo.manager.crud;

import com.antilia.web.dialog.ModalContainer;


public class CityPage extends CRUDPage {

	private static final long serialVersionUID = 1L;	
	
	public CityPage() {		
		super();			
	}

	@Override
	protected ModalContainer newModalContainer(String id) {
		return new CityModalContainer(id);
	}	
}
