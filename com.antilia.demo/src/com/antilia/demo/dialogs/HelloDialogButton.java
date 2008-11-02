/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import org.apache.wicket.ResourceReference;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HelloDialogButton extends DialogButton {

	private static final long serialVersionUID = 1L;

	public HelloDialogButton(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		HelloDialog dialog = new HelloDialog(id);
		dialog.setResizable(false);
		dialog.setFoldable(false);
		dialog.setModal(true);
		return dialog;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Hello!";
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	protected String getTitleKey() {
		return null;
	}

}
