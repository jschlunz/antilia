/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.export;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AbstractExportDialogButton extends DialogButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient AbstractExportTask exportTask;
	
	/**
	 * @param id
	 */
	public AbstractExportDialogButton(String id) {
		super(id);
		setCacheDialog(false);
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		exportTask = newExportTask();
		Thread thread = new Thread(exportTask);
		thread.start();
		super.onSubmit(target, form);		
	}
	
	protected abstract AbstractExportTask newExportTask();
	
	
	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		DefaultDialog dialog =new DefaultDialog(id, this) {
			private static final long serialVersionUID = 1L;

			@Override
			public IModel<String> getTitle() {
				return AbstractExportDialogButton.this.getTitle();
			}
			
			@Override
			protected Component createBody(String id) {
				return new DefaultExportPanel(id, AbstractExportDialogButton.this) {
					
					private static final long serialVersionUID = 1L;

					@Override
					public String getContentType() {
						return AbstractExportDialogButton.this.getContentType();
					}
					
					@Override
					public String getDowloadMessage() {
						return AbstractExportDialogButton.this.getDowloadMessage();
					}
				};
			}
		};
		dialog.setModal(true);
		dialog.setWidth(300);
		dialog.setHeight(200);
		dialog.setResizable(false);
		dialog.setCentered(true);
		return dialog;
	}

	/**
	 * @return the exportPdfTask
	 */
	public AbstractExportTask getExportTask() {
		return exportTask;
	}	

	public abstract String getContentType();
	
	public abstract String getDowloadMessage();
	
	protected abstract IModel<String> getTitle();
	
}
