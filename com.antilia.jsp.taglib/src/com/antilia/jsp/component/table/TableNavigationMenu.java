/**
 * 
 */
package com.antilia.jsp.component.table;

import java.io.Serializable;

import com.antilia.jsp.component.MenuComponent;

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
		addMenuItem(new FirstPageLink<E>("First", tableComponent));
		addMenuItem(new PreviousPageLink<E>("Previous", tableComponent));
		addMenuItem(new NextPageLink<E>("Next", tableComponent));
		addMenuItem(new LastPageLink<E>("Last", tableComponent));
	}

}
