/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IColumnModel<E extends Serializable> extends IModel<E> {
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
		
	
	/**
	 * @return Whether the column is re-sizable or not;	 
	 */
	public boolean isResizable();

	/**
	 * Flag to set column resizability.
	 * 
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable);
	
	/**
	 * The property path asociated with the column.
	 * @return The property path
	 */		
	String getPropertyPath();
	
	/**
	 * @return Returns the table model.
	 */
	ITableModel<E> getTableModel();
	
	
	/**
	 * Returns a model for the column title.
	 * 
	 * @param component
	 * @return
	 */
	IModel<String> getTitleModel(Component component);
	
	
	/**
	 * Get the renderer (might be null). 
	 */
	IColumnRenderer<E> getRenderer();
	
	/**
	 * Sets the renderer.
	 * @param renderer
	 */
	void setRenderer(IColumnRenderer<E> renderer);
	
}
