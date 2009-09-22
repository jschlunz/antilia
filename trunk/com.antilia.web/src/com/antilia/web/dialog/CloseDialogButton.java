/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CloseDialogButton extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog; 
	
	public CloseDialogButton(String id, DefaultDialog dialog) {
		super(id, true);
		this.dialog = dialog;
	}
	
	
	public CloseDialogButton(DefaultDialog dialog) {
		this("close", dialog);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_CLOSE;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "";
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	protected String getTitleKey() {
		return "CloseDialogButton.title";
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		new CloseDialogAction(this, dialog).onSubmit(target, form, "close");
	}


	@Override
	public IAjaxCallDecorator getAjaxCallDecorator() {		
		return new CloseDialogAction(this, dialog).getAjaxCallDecorator();
	}
}
