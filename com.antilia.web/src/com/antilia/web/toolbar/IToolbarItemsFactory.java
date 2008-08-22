/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.toolbar;

import java.io.Serializable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IToolbarItemsFactory  extends Serializable {

	void populateMenuItems(String menuId, IToolbar  toolbar);
	
}
