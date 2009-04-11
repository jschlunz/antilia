/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import com.antilia.web.layout.ToolBarFullPage;
import com.antilia.web.login.IProtectedPage;
import com.antilia.web.toolbar.Toolbar;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Index extends ToolBarFullPage implements IProtectedPage {

	private static final long serialVersionUID = 1L;	
	
	public Index() {		
		super();			
		
	}

	@Override
	protected Toolbar createToolbar(String id, ToolBarFullPage page) {
		return Toolbar.createToolbar(id, new MainMenuFactory(this));
	}
}
