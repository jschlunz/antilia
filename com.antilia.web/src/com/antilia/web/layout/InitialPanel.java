/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import org.apache.wicket.Component;

import com.antilia.web.dialog.ModalContainer;
import com.antilia.web.toolbar.Toolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class InitialPanel extends ModalContainer {

	private static final long serialVersionUID = 1L;

	private ToolBarFullPage page;
	/**
	 * @param id
	 */
	public InitialPanel(String id, ToolBarFullPage page) {
		super(id);
		this.page = page;
	}
	
	@Override
	protected Component createBody(String id) {
		return 	createToolbar(id, page, getId());
	}
	
	
	protected abstract Toolbar createToolbar(String id, ToolBarFullPage page, String contentId);
}
