/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.AbstractButton;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class ConfirmationPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ConfirmationPanel(String id, String message) {
		super(id);
		
		add(new Label("message", message));
		add(newOkButton("ok"));
		add(newCancelButton("cancel"));
	}
	
	
	abstract AbstractButton newCancelButton(String id);
	
	abstract AbstractButton newOkButton(String id);
}
