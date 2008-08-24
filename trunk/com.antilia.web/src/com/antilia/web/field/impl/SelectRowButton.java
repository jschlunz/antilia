/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.util.OklDialogButton;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SelectRowButton extends OklDialogButton {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param dialog
	 */
	public SelectRowButton(String id, DefaultDialog dialog) {
		super(id, dialog);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.util.OklDialogButton#onOk(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.form.Form)
	 */
	@Override
	protected void onOk(AjaxRequestTarget target, Form form) {
		
	}
	
	@Override
	protected String getLabel() {
		return null;
	}
	
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_BACK;
	}

}
