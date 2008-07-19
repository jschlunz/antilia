/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;

import org.apache.wicket.model.Model;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ColumnModel<E extends Serializable> extends Model implements IColumnModel<E> {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 200;
	
	private int width = DEFAULT_WIDTH;
		
	private String propertyPath;
	
	private ITableModel<E> tableModel;
	
	private boolean sortable;
		
	/**
	 * 
	 */
	public ColumnModel(ITableModel<E> tableModel, final String propertyPath) {
		this(tableModel, DEFAULT_WIDTH,1, propertyPath);
	}
	
	/**
	 * 
	 */
	public ColumnModel(ITableModel<E> tableModel, int width, int colSpan, final String propertyPath) {
		super();
		this.tableModel = tableModel;
		this.width = width;
		this.propertyPath = propertyPath;		
	}

	
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	public String getPropertyPath() {
		return propertyPath;
	}

	/**
	 * @param propertyPath the propertyPath to set
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	public ITableModel<E> getTableModel() {
		return tableModel;
	}

	public void setTableModel(ITableModel<E> tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

}
