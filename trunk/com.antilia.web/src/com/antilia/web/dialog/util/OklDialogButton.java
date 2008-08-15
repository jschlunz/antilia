/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog.util;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.dialog.CloseDialogAction;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class OklDialogButton extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog; 
	
	public OklDialogButton(String id,DefaultDialog dialog) {
		super(id, true);
		this.dialog = dialog;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_OK;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Ok";
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
			new CloseDialogAction(this, dialog).onSubmit(target, form);
			onOk(target, form);
	}

	protected abstract void onOk(AjaxRequestTarget target, Form form);

	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new CloseDialogAction(this, dialog).getAjaxCallDecorator();
	}
}
