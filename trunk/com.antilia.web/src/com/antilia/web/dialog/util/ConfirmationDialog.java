/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog.util;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.dialog.DialogStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public  class ConfirmationDialog extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	private IModel<String> message;
	
	/**
	 * @param id
	 * @param button
	 */
	public ConfirmationDialog(String id, DialogButton button, String message) {
		this(id, button, new Model<String>(message));
	}
	
	
	public ConfirmationDialog(String id, DialogButton button, IModel<String> message) {
		super(id, button);
		this.message = message;		
		init();
	}
	

	/**
	 * @param id
	 * @param button
	 * @param dialogStyle
	 */
	public ConfirmationDialog(String id, DialogButton button, DialogStyle dialogStyle, String message) {
		this(id, button, dialogStyle, new Model<String>(message));
	}
	
	/**
	 * @param id
	 * @param button
	 * @param dialogStyle
	 */
	public ConfirmationDialog(String id, DialogButton button, DialogStyle dialogStyle, IModel<String> message) {
		super(id, button, dialogStyle);
		this.message = message;
		init();
	}
	
	@Override
	public IModel<String> getTitle() {
		return new ResourceModel("dialogTitle");
	}
	
	private void init() {
		setWidth(420);
		setHeight(150);
		setResizable(false);
		setFoldable(false);
		setModal(true);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#createBody(java.lang.String)
	 */
	@Override
	protected Component createBody(String id) {
		return new ConfirmationPanel(id) {
			
			private static final long serialVersionUID = 1L;

			@Override
			AbstractButton newCancelButton(String id) {
				return ConfirmationDialog.this.newCancelButton(id);
			}
			
			@Override
			AbstractButton newOkButton(String id) {
				return ConfirmationDialog.this.newOkButton(id);
			}
			
			@Override
			IModel<String>getMessage() {
				return ConfirmationDialog.this.message;
			}
		};
	}
	
	protected AbstractButton newCancelButton(String id) {
		return new CancelDialogButton(id, this);
	}
	
	protected  AbstractButton newOkButton(String id) {
		return new OkDialogButton(id, this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onOk(AjaxRequestTarget target, Form<?> form) {
				ConfirmationDialog.this.onOk(target, form);
			}
			
		};
	}
	
	protected void onOk(AjaxRequestTarget target, Form<?> form) {
		
	}	
}
