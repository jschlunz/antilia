/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class SelectionCRUDPanel<B extends Serializable> extends CRUDPanel<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param beanClass
	 */
	public SelectionCRUDPanel(String id, Class<B> beanClass) {
		super(id, beanClass);
	}

	/**
	 * @param id
	 * @param styler
	 */
	public SelectionCRUDPanel(String id, CrudStyler<B> styler) {
		super(id, styler);
	}
	
	@Override
	protected SearchPanel<B> newSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected Table<B> newTable(String id, ITableModel<B> tableModel,
					IPageableProvider<B> pageableProvider) {
				
				return  new Table<B>(id,tableModel, pageableProvider) {

					private static final long serialVersionUID = 1L;
					
					@Override
					public void populateRowMenu(IMenuItemHolder menu, int row, B bean) {
						SelectionCRUDPanel.this.populateRowMenu(menu, row, bean);
					}
					
				};
			}
		};
	}
	
	public abstract void populateRowMenu(IMenuItemHolder menu, int row, B bean);

}
