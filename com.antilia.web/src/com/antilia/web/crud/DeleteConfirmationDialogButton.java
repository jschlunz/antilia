/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import org.apache.wicket.ResourceReference;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.dialog.util.ConfirmationDialog;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DeleteConfirmationDialogButton extends DialogButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public DeleteConfirmationDialogButton() {
		super("delete");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_DELETE;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Delete";
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		return new ConfirmationDialog(id, this, "Do you want to delete records?");
	}

}
