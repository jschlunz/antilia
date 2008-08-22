/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import org.apache.wicket.Component;

import com.antilia.web.dialog.ModalContainer;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CountryModalContainer extends ModalContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public CountryModalContainer(String id) {
		super(id);
		setRenderBodyOnly(true);
	}


	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.ModalContainer#createBody(java.lang.String)
	 */
	@Override
	protected Component createBody(String id) {
		return new CountryCRUDPanel(id);
	}
	
}
