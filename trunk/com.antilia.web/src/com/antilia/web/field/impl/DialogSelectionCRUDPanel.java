/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.crud.CrudStyler;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DialogSelectionCRUDPanel<B extends Serializable> extends SelectionCRUDPanel<B> {

	private static final long serialVersionUID = 1L;
	
	private LargeSelectionDialog<B> selectionDialog;
	
	/**
	 * @param id
	 * @param beanClass
	 */
	public DialogSelectionCRUDPanel(String id, Class<B> beanClass, LargeSelectionDialog<B> selectionDialog) {
		super(id, beanClass);
		this.selectionDialog = selectionDialog;
	}

	/**
	 * @param id
	 * @param styler
	 */
	public DialogSelectionCRUDPanel(String id, CrudStyler<B> styler) {
		super(id, styler);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.impl.SelectionCRUDPanel#populateRowMenu(com.antilia.web.button.IMenuItemHolder, int, java.io.Serializable)
	 */
	@Override
	public void populateRowMenu(IMenuItemHolder menu, int row, B bean) {
		menu.addMenuItem(new SelectRowButton("selectRow", selectionDialog));
	}

}
