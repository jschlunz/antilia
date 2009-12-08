/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.letsplay;

import com.antilia.web.toolbar.IToolbar;
import com.antilia.web.toolbar.IToolbarItemsFactory;
import com.antilia.web.toolbar.SubToolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MainMenuFactory implements IToolbarItemsFactory {

	private static final long serialVersionUID = 1L;

	private Index page;
		
	/**
	 * 
	 */
	public MainMenuFactory(Index page) {
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.IToolbarItemsFactory#populateMenuItems(java.lang.String, com.antilia.web.toolbar.IToolbar)
	 */
	public void populateMenuItems(String menuId, IToolbar toolbar) {
			SubToolbar subToolbar = new SubToolbar("Games", toolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Games";
				}
			};
			toolbar.addItem(subToolbar);
			
			
	}

	public Index getPage() {
		return page;
	}

}
