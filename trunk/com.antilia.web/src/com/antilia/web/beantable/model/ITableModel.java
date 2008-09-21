/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;

import com.antilia.web.beantable.provider.SelectionMode;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface ITableModel<E extends Serializable> extends IModel {
	
	Iterator<IColumnModel<E>> getColumnModels();
	
	void setColumnModels(List<IColumnModel<E>> models);
	
	Iterator<IColumnModel<E>> getHiddenModels();
	
	void setHiddenModels(List<IColumnModel<E>> models);
	
	boolean swapColumns(int i, int j);
	
	boolean hideColumn(int i);
	
	int getColumns();
	
	void setSelectionModel(SelectionMode selectionModel);
	
	SelectionMode getSelectionModel();
	
	Class<E> getBeanClass();
	
	public IComponentInheritedModel newModel(E object);
}