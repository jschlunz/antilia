/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;

import com.antilia.web.provider.SelectionMode;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface ITableModel<E extends Serializable> extends IModel<ArrayList<IColumnModel<E>>> {
	
	Iterator<IColumnModel<E>> getColumnModels();
	
	IColumnModel<E> getColumnModel(int index);
	
	void setColumnModels(List<IColumnModel<E>> models);
	
	Iterator<IColumnModel<E>> getHiddenModels();
	
	void setHiddenModels(List<IColumnModel<E>> models);
	
	boolean swapColumns(int i, int j);
	
	boolean moveColumnBefore(int toMove, int before);
	
	boolean hideColumn(int i);
	
	int getColumns();
	
	void setSelectionMode(SelectionMode selectionModel);
	
	SelectionMode getSelectionMode();
	
	Class<E> getBeanClass();
	
	public IComponentInheritedModel<E> newModel(IModel<E> object);
	
	public void addColumnRenderer(IColumnRenderer<E> columnRenderer);
}
