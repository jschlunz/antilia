/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.navigator.IPageableNavigator;
import com.antilia.web.provider.IQuerableDataProvider;

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
	
	
	public SelectionCRUDPanel(String id, Class<B> beanClass,  CRUDPanel<Serializable> parentCrud) {
		super(id,beanClass, parentCrud);
	}	
	
	public SelectionCRUDPanel(String id, CrudStyler<B> styler, CRUDPanel<Serializable> parentCrud) {
		super(id, styler, parentCrud);
	}
	
	@Override
	protected SearchPanel<B> newSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected Table<B> newTable(String id, ITableModel<B> tableModel,
					IPageableNavigator<B> pageableProvider) {
				
				return  new Table<B>(id,tableModel, pageableProvider) {

					private static final long serialVersionUID = 1L;
					
					@Override
					public void populateRowMenu(IMenuItemHolder menu, int row, B bean) {
						SelectionCRUDPanel.this.populateRowMenu(menu, row, bean);
					}
					
				};
			}
			
			@Override
			protected void configureColumnModel(IColumnModel<B> model) {
				SelectionCRUDPanel.this.configureColumnModel(model);
			}
			
			@Override
			protected IQuerableDataProvider<B> createPageableProvider( IQuery<B> filterQuery) {
				return SelectionCRUDPanel.this.createPageableProvider(filterQuery);
			}
		};
	}
	
	public abstract void populateRowMenu(IMenuItemHolder menu, int row, B bean);

}
