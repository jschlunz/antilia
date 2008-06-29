/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;

import com.antilia.web.beantable.provider.SelectionMode;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface ITableModel<E extends Serializable> extends IModel {
	
	Iterator<IColumnModel<E>> getColumnModels();
	
	int getColumns();
	
	void setSelectionModel(SelectionMode selectionModel);
	
	SelectionMode getSelectionModel();
	
	Class<E> getBeanClass();
	
	public IComponentInheritedModel newModel(E object);
}
