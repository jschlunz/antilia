/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IColumnModel<E extends Serializable> extends IModel {
	/**
	 * Gets the width (in pixels) of the column
	 * @return
	 */
	int getWidth();
	
	
	/**
	 * Set the with 
	 * @param width
	 */
	void setWidth(int width);
	
	
	/**
	 * If the column is Sortable....
	 * @return
	 */
	public boolean isSortable();

	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable);
		
	String getPropertyPath();
	
	ITableModel<E> getTableModel();
}
