/**
 * 
 */
package com.antilia.web.menu;

import java.io.Serializable;

import com.antilia.web.button.IMenuItem;

/**
 *  Allows to filer menu items based on some criteria.
 *  
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public interface IMenuItemsAuthorizer extends Serializable {
	
	/**
	 * @param menuItem The menu item.
	 * @return True if <code>menuItem</code> is authorized to appear, false otherwise.
	 */
	boolean isMenuItemAuthorized(IMenuItem menuItem);

}
