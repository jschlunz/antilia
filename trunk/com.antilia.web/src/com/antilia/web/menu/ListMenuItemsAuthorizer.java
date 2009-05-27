/**
 * 
 */
package com.antilia.web.menu;

import com.antilia.common.osgi.Aggregator;
import com.antilia.web.button.IMenuItem;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ListMenuItemsAuthorizer extends Aggregator<Class<? extends IMenuItem>> implements IMenuItemsAuthorizer {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor accepting a list of classes.
	 */
	public ListMenuItemsAuthorizer() {		
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.menu.IMenuItemsAuthorizer#isMenuItemAuthorized(com.antilia.web.button.IMenuItem)
	 */
	public boolean isMenuItemAuthorized(IMenuItem menuItem) {
		Class<?> clazz = menuItem.getClass();
		for(Class<? extends IMenuItem> c: elements()) {
			if(c.isAssignableFrom(clazz))
				return false;
		}
		return true;
	}
	
	@Override
	public ListMenuItemsAuthorizer add(Class<? extends IMenuItem> a) {
		super.add(a);
		return this;
	}
}


