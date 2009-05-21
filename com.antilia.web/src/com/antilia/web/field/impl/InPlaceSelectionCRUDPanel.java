/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.IQuerableDataProvider;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.CRUDTitle;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.crud.SearchPanelButtonsFactory;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.menu.Menu;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InPlaceSelectionCRUDPanel<B extends Serializable> extends SelectionCRUDPanel<B> {

	private static final long serialVersionUID = 1L;
		
	private BeanProxy<B> beanProxy; 
	private IFieldModel<B> fieldModel;
	
	/**
	 * @param id
	 * @param beanClass
	 */
	public InPlaceSelectionCRUDPanel(String id, CRUDPanel<Serializable> parent, Class<B> beanClass,  BeanProxy<B> beanProxy, IFieldModel<B> fieldModel) {
		super(id, beanClass, parent);
		this.beanProxy  = beanProxy;
		this.fieldModel = fieldModel;
	}

	/**
	 * @param id
	 * @param styler
	 */
	public InPlaceSelectionCRUDPanel(String id, CRUDPanel<Serializable> parent,CrudStyler<B> styler, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel) {
		super(id, styler, parent);
		this.beanProxy  = beanProxy;
		this.fieldModel = fieldModel;
	}
	
	@Override
	protected Component newBeforeBody(String id, CrudStyler<B> styler) {
		List<CRUDTitle.CRUDInfo> classes = new ArrayList<CRUDTitle.CRUDInfo>();
		getTitlePath(classes, this);
		return new CRUDTitle(id, classes);
	}

	@SuppressWarnings("unchecked")
	private void getTitlePath(List<CRUDTitle.CRUDInfo> classes, CRUDPanel crud) {
		 if(crud.getParentCrud() != null) {
			 getTitlePath(classes, crud.getParentCrud());			 
		 }
		 classes.add(new CRUDTitle.CRUDInfo(crud.getStyler().getBeanClass(), crud.getCrudMode()));
	}
	
	@Override
	protected SearchPanel<B> newSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Menu newTopMenuMenu(String id) {
				return Menu.createMenu(id, 
						null,
						new IMenuItemsFactory() {
					
					private static final long serialVersionUID = 1L;

					public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
						itemHolder.addMenuItem(new InPlaceBackToParentButton<B>("home", getParentCrud() ));
					}
					
				},SearchPanelButtonsFactory.getInstance());
			}
			
			@Override
			protected void configureColumnModel(IColumnModel<B> model) {
				InPlaceSelectionCRUDPanel.this.configureColumnModel(model);
			}
			
			@Override
			protected IQuerableDataProvider<B> createPageableProvider( IQuery<B> filterQuery) {
				return InPlaceSelectionCRUDPanel.this.createPageableProvider(filterQuery);
			}
						
			
			@Override
			protected Table<B> newTable(String id, ITableModel<B> tableModel,
					IPageableProvider<B> pageableProvider) {
				
				return  new Table<B>(id,tableModel, pageableProvider) {

					private static final long serialVersionUID = 1L;
					
					@Override
					public void populateRowMenu(IMenuItemHolder menu, int row, B bean) {
						InPlaceSelectionCRUDPanel.this.populateRowMenu(menu, row, bean);
					}
					
				};
			}
						
		};
	}
		

	/* (non-Javadoc)
	 * @see com.antilia.web.field.impl.SelectionCRUDPanel#populateRowMenu(com.antilia.web.button.IMenuItemHolder, int, java.io.Serializable)
	 */
	@Override
	public void populateRowMenu(IMenuItemHolder menu, int row, B bean) {
		menu.addMenuItem(new InPlaceSelectRowButton<B>("selectRow", getParentCrud() , this.beanProxy, this.fieldModel, bean));
	}

}
