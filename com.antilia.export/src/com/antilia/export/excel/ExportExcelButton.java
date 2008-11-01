/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.excel;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;

import com.antilia.web.beantable.Table;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.export.AbstractExportDialogButton;
import com.antilia.web.export.AbstractExportTask;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportExcelButton<B extends Serializable> extends AbstractExportDialogButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public ExportExcelButton(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_EXPORT_EXCEL;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}
	@Override
	public String getContentType() {
		return "application/excel";
	}

	@Override
	protected AbstractExportTask newExportTask() {
		Table<B> table = findPageableComponent();
		return new ExportExcelTask<B>(table.getPageableProvider(), table.getTableModel());
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		DefaultDialog dialog = super.newDialog(id);
		dialog.setTitle("Exporting to Excel...");
		return dialog;
	}

	
	@SuppressWarnings("unchecked")
	private Table<B> findPageableComponent() {
		return (Table<B>)findParent(Table.class);
	}
	
	@Override
	public String getDowloadMessage() {
		return "Click here to see the generated Excel document";
	}
}
