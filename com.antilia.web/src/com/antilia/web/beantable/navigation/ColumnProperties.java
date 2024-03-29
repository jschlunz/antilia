/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;

import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.menu.DropDownButton;
import com.antilia.web.menu.DropDownMenu;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class ColumnProperties<E extends Serializable> extends DropDownButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ColumnProperties() {
		super("columnsProperties");
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.menu.DropDownButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_DOWN;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.menu.DropDownButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.menu.DropDownButton#newMenu(java.lang.String)
	 */
	@Override
	public DropDownMenu newMenu(String id) {		
		return new DropDownMenu(id, 
				new IMenuItemsFactory() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			public void populateMenuItems(String menuId,
					IMenuItemHolder itemHolder) {
				if(getColumnModel() != null) {
					itemHolder.addMenuItem( new SortAscendingButton("ascending", getColumnModel()));	
					itemHolder.addMenuItem( new SortDescendingButton("descending", getColumnModel()));
				}
			}
		}
		);
	}

	/**
	 * @return the columnModel
	 */
	public abstract IColumnModel<E> getColumnModel();


}
