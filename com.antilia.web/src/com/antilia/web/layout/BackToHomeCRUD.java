/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import java.io.Serializable;

import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.crud.TitledCRUDPanel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class BackToHomeCRUD<B extends Serializable> extends TitledCRUDPanel<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param beanClass
	 */
	public BackToHomeCRUD(String id, Class<B> beanClass) {
		super(id, beanClass);	
	}

	/**
	 * @param id
	 * @param styler
	 */
	public BackToHomeCRUD(String id, CrudStyler<B> styler) {
		super(id, styler);
	}

	
	@Override
	protected SearchPanel<B> newSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler) {
			
			private static final long serialVersionUID = 1L;

			
			@Override
			protected void addItemsTopMenuBeforeSearchButtons(String menuId,IMenuItemHolder itemHolder) {
				itemHolder.addMenuItem(new HomeLink("home"));
			}
			
			@Override
			protected void configureColumnModel(IColumnModel<B> model) {
				BackToHomeCRUD.this.configureColumnModel(model);
			}
		};
	}
	
}
