/**
 * 
 */
package com.antilia.jsp.component;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class TableNavigationMenu<E extends Serializable> extends MenuComponent {

	/**
	 * @param id
	 */
	public TableNavigationMenu(String id, TableComponent<E> tableComponent) {
		super(id);
		addMenuItem(new PreviousPageLink<E>("Previous", tableComponent));
		addMenuItem(new NextPageLink<E>("Next", tableComponent));
	}

}
