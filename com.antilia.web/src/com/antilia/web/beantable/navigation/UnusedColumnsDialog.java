/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DefaultDialogStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class UnusedColumnsDialog<E extends Serializable> extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public UnusedColumnsDialog(String id) {
		super(id, null,  new DefaultDialogStyle().setBackgroundColor("#d73957").setBodyColor("#f1d0d6"));
		setPosX(10);
		setPosY(10);
		setWidth(300);
		setHeight(210);
		setTitle("Table Columns");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#populateBody(org.apache.wicket.markup.html.WebMarkupContainer)
	 */
	@Override
	protected Component createBody(String id) {
		return new UnusedColumnsPanel<E>(id);
	}

}
