/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog.util;

import org.apache.wicket.Component;

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

	private String message;
	
	/**
	 * @param id
	 * @param button
	 */
	public ConfirmationDialog(String id, DialogButton button, String message) {
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
		super(id, button, dialogStyle);
		this.message = message;
		init();
	}
	
	private void init() {
		setWidth(200);
		setHeight(150);
		setResizable(false);
		setFoldable(false);
		setModal(true);
		setTitle("Confirmation Dialog");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#createBody(java.lang.String)
	 */
	@Override
	protected Component createBody(String id) {
		return new ConfirmationPanel(id, message) {
			
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
			String getMessage() {
				return ConfirmationDialog.this.message;
			}
		};
	}
	
	protected AbstractButton newCancelButton(String id) {
		return new CancelDialogButton(id, this);
	}
	
	protected  AbstractButton newOkButton(String id) {
		return new OklDialogButton(id, this);
	}
}
