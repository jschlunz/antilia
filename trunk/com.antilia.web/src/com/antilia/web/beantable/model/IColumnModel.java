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
	
	int getWidth();
	
	void setWidth(int width);
		
	String getPropertyPath();
	
	ITableModel<E> getTableModel();
}
