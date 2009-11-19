/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

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
		super(id, null,  new DefaultDialogStyle());
		setPosX(10);
		setPosY(10);
		setWidth(320);
		setHeight(240);
		setCentered(true);
	}
	
	@Override
	public IModel<String> getTitle() {
		return new ResourceModel("UnusedColumnsDialog.title");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#populateBody(org.apache.wicket.markup.html.WebMarkupContainer)
	 */
	@Override
	protected Component createBody(String id) {
		return new UnusedColumnsPanel<E>(id, this);
		//return new ColumnModelPalette<E>(id);
	}

}
