/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import java.io.Serializable;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IMenuItemsFactory  extends Serializable {

	void populateMenuItems(String menuId, IMenuItemHolder  itemHolder);
	
}
