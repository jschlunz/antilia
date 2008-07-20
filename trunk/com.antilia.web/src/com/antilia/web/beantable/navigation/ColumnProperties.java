/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import org.apache.wicket.ResourceReference;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.button.SeparatorButton;
import com.antilia.web.menu.DropDownButton;
import com.antilia.web.menu.DropDownMenu;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ColumnProperties extends DropDownButton {

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
			
			@Override
			public void populateMenuItems(String menuId,
					IMenuItemHolder itemHolder) {
				itemHolder.addMenuItem(new FirstPageButton());
				itemHolder.addMenuItem(new PreviousPageButton());
				itemHolder.addMenuItem(new PageNumberItem());
				itemHolder.addMenuItem(new NextPageButton());
				itemHolder.addMenuItem(new LastPageButton());
				
				itemHolder.addMenuItem(new SeparatorButton());		
				itemHolder.addMenuItem(new PageSizeButton());
				itemHolder.addMenuItem(new RefreshButton());
			}
		}
		);
	}

}
