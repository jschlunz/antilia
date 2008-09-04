/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class ColumnMenuItemsFactory<E extends Serializable> implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;
	
	public ColumnMenuItemsFactory() {
	}
	
	@SuppressWarnings("unchecked")
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		itemHolder.addMenuItem(new SortColumnButton("sortColumn", getColumnModel()));
		itemHolder.addMenuItem(new SortAscendingButton("sortAscColumn", getColumnModel()));
		itemHolder.addMenuItem(new SortDescendingButton("sortDescColumn", getColumnModel()));
		/*
		itemHolder.addMenuItem(new ColumnProperties() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public IColumnModel getColumnModel() {
				return ColumnMenuItemsFactory.this.getColumnModel();
			}
		});	
		*/	
	}

	/**
	 * @return the columnModel
	 */
	public abstract IColumnModel<E> getColumnModel();

}
