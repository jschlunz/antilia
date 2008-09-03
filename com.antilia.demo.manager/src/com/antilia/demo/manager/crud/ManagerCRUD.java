/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.crud;

import java.io.Serializable;

import com.antilia.demo.manager.HomeLink;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.crud.SearchPanelButtonsFactory;
import com.antilia.web.crud.TitledCRUDPanel;
import com.antilia.web.menu.Menu;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ManagerCRUD<B extends Serializable> extends TitledCRUDPanel<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param beanClass
	 */
	public ManagerCRUD(String id, Class<B> beanClass) {
		super(id, beanClass);	
	}

	/**
	 * @param id
	 * @param styler
	 */
	public ManagerCRUD(String id, CrudStyler<B> styler) {
		super(id, styler);
	}

	@Override
	protected SearchPanel<B> newSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Menu newTopMenuMenu(String id) {
				return Menu.createMenu(id, new IMenuItemsFactory() {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
						itemHolder.addMenuItem(new HomeLink("home"));
					}
					
				},SearchPanelButtonsFactory.getInstance());
			}
			
			@Override
			protected void configureColumnModel(IColumnModel<B> model) {
				ManagerCRUD.this.configureColumnModel(model);
			}
		};
	}
	
}
