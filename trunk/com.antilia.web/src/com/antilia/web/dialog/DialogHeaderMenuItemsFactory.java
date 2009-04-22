/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DialogHeaderMenuItemsFactory implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog;
	
	public DialogHeaderMenuItemsFactory(DefaultDialog dialog) {
		this.dialog = dialog;
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		itemHolder.addMenuItem(new FoldButton(this.dialog));
		itemHolder.addMenuItem(new CloseDialogLink(this.dialog));
	}
}
