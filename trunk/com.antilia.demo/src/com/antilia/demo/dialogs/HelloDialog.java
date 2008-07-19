/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DefaultDialogStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class HelloDialog extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public HelloDialog(String id) {
		super(id, null,  new DefaultDialogStyle().setBackgroundColor("#d73957").setBodyColor("#f1d0d6"));
		setPosX(10);
		setPosY(10);
		setWidth(300);
		setHeight(210);
		setTitle("List of Patients");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#populateBody(org.apache.wicket.markup.html.WebMarkupContainer)
	 */
	@Override
	protected Component createBody(String id) {
		return new Label(id, "Hello World");
	}

}
