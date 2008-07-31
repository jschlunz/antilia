/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class DialogButton extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog;
	
	private int order = AbstractButton.NO_ORDER;
	
	private AbstractButton button;
	
	public DialogButton(String id) {
		super(id);
		button = new AbstractButton("button", true){			
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImage() {
				return DialogButton.this.getImage();
			}
			
			 @Override
			protected String getLabel() {
				return DialogButton.this.getLabel();
			}
			 
			 @Override
			public boolean isEnabled() {
				return !DialogButton.this.dialog.isVisible();
			}
			 
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				DialogButton.this.onSubmit(target, form);
			}
		};
		
		add(button);
		
		dialog = newDialog("dialog"); 
		dialog.setVisible(false);				
		dialog.setOutputMarkupPlaceholderTag(true);
		dialog.setDialogButton(this);
		add(dialog);
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();

	
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		if(showDialog(target, form)) {
			dialog.setVisible(true);
			target.addComponent(dialog);
			target.addComponent(button);
		}
	}
	

	/**
	 * Override this function if you want to check a condition in order to show the dialog.
	 * By default  the function returns true.
	 * 
	 * @param target
	 * @param form
	 * @return
	 */
	protected boolean showDialog(AjaxRequestTarget target, Form form) {
		return true;
	}
	
	public abstract DefaultDialog newDialog(String id);

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public AbstractButton getButton() {
		return button;
	}
}
