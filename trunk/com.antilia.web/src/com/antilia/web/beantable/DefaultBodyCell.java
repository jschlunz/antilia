/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.antilia.web.beantable.model.IColumnModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultBodyCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;


	/**
	 * @param id
	 * @param model
	 */
	public DefaultBodyCell(String id, IColumnModel<E> columnModel, E object) {
		super(id, columnModel);		
		add(new Label("cell",newBodyCellModel(columnModel, object)));
	}
	
	
	protected IModel newBodyCellModel(IColumnModel<E> columnModel, E object) {
		return new PropertyModel(object, columnModel.getPropertyPath());
	}

	@SuppressWarnings("unchecked")
	protected IColumnModel<E> getColumnModel() {
		return (IColumnModel<E>)getModel();
	}
}
