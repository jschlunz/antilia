/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SortColumnItem<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	/**
	 * @param id
	 */
	public SortColumnItem(IColumnModel<E> columnModel) {
		super("sortColumns");		
		
		SortAscendingButton<E> ascendingButton = new SortAscendingButton<E>("ascending",columnModel.getPropertyPath());
		add(ascendingButton);
		
		
		SortDescendingButton<E> descendingButton = new SortDescendingButton<E>("descending", columnModel.getPropertyPath());
		add(descendingButton);
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
