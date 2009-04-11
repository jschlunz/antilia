/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager.components.tables;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.layout.BackToHomeTopMenuPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TablesPanel extends BackToHomeTopMenuPanel<Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablesPanel(String id) {
		super(id);
	}
	
	@Override
	protected Component createBody(String id) {
		return new TablePanel(id);
	}

}
