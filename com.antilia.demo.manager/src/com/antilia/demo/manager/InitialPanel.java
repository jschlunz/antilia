/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.toolbar.Toolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InitialPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public InitialPanel(String id, Index page) {
		super(id);
		setOutputMarkupId(true);
		Toolbar toolbar = Toolbar.createToolbar("menu", new MainMenuFactory(page, id));
		add(toolbar);
	}
}
