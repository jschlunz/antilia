/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ColumnModel<E extends Serializable> extends Model<E> implements IColumnModel<E> {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 200;
	
	private int width = DEFAULT_WIDTH;
		
	private String propertyPath;
	
	private ITableModel<E> tableModel;
	
	/**
	 * This property says if a column is sortable;
	 */
	private boolean sortable = true;
	
	/**
	 * Whether the column is re-sizable or not;
	 */
	private boolean resizable = true;
	
	/**
	 * 
	 */
	private IColumnRenderer<E> renderer;
			
	/**
	 * 
	 */
	public ColumnModel(ITableModel<E> tableModel, final String propertyPath) {
		this(tableModel, DEFAULT_WIDTH, propertyPath);
	}
	
	/**
	 * 
	 */
	public ColumnModel(ITableModel<E> tableModel, int width,  final String propertyPath) {
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

	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public IModel<String> getTitleModel(Component component) {
		String key = ResourceUtils.getPropertyResourceKey(tableModel.getBeanClass(), getPropertyPath());
		return new StringResourceModel(key, component, null, getPropertyPath());
	}

	public IColumnRenderer<E> getRenderer() {
		return renderer;
	}

	public void setRenderer(IColumnRenderer<E> renderer) {
		this.renderer = renderer;
	}
}
