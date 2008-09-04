/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import org.apache.wicket.Component;

import com.antilia.web.dialog.ModalContainer;
import com.antilia.web.toolbar.Toolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InitialPanel extends ModalContainer {

	private static final long serialVersionUID = 1L;

	private Index page;
	/**
	 * @param id
	 */
	public InitialPanel(String id, Index page) {
		super(id);
		this.page = page;
	}
	
	@Override
	protected Component createBody(String id) {
		return 	Toolbar.createToolbar(id, new MainMenuFactory(page, getId()));
	}
}
