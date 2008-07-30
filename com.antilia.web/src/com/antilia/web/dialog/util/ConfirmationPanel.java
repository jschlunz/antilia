/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ConfirmationPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ConfirmationPanel(String id, String message) {
		super(id);
		
		add(new Label("message", message));
	}
	
}
