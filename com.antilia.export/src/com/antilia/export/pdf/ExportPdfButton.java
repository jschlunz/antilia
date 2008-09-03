/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.hibernate.context.RequestContext;
import com.antilia.web.beantable.Table;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPdfButton<B extends Serializable> extends DialogButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExportPdfTask<B> exportPdfTask;
	
	/**
	 * @param id
	 */
	public ExportPdfButton(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_EXPORT_PDF;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		super.onSubmit(target, form);
		Table<B> table = findPageableComponent();
		exportPdfTask = new ExportPdfTask<B>(table.getPageableProvider(), RequestContext.get().getPersistenceUnit(), RequestContext.get().getUser(), table.getTableModel());
		Thread thread = new Thread(exportPdfTask);
		thread.start();
	}
	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		DefaultDialog dialog =new DefaultDialog(id, this) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Component createBody(String id) {
				return new ExportPdfPanel<B>(id, ExportPdfButton.this);
			}
		};
		dialog.setTitle("Exporting to PDF...");
		dialog.setModal(true);
		dialog.setWidth(300);
		dialog.setHeight(200);
		dialog.setResizable(false);
		return dialog;
	}

	/**
	 * @return the exportPdfTask
	 */
	public ExportPdfTask<B> getExportPdfTask() {
		return exportPdfTask;
	}
	
	@SuppressWarnings("unchecked")
	private Table<B> findPageableComponent() {
		return (Table<B>)findParent(Table.class);
	}
}
