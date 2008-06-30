/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import org.apache.wicket.Component;

import com.antilia.web.dialog.DefaultDialog;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TestDialog extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TestDialog(String id) {
		super(id);
		setPosX(400);
		setTitle("List of Patients");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#populateBody(org.apache.wicket.markup.html.WebMarkupContainer)
	 */
	@Override
	protected Component createBody(String id) {
		return new PersonCRUD(id);
	}

}
