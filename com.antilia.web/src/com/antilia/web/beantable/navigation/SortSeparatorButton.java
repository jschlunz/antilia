/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.SmallSeparatorButton;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SortSeparatorButton<E extends Serializable> extends SmallSeparatorButton {

	private static final long serialVersionUID = 1L;

	private IColumnModel<E> columnModel;
	
	public SortSeparatorButton(IColumnModel<E> columnModel) {
		super();
		this.columnModel = columnModel;
	}
		
	@Override
	public boolean isVisible() {
		if(!this.columnModel.isSortable()) {
			return true;
		}
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableNavidator().getQuery();
		IOrder<E> order = query.getOrder(columnModel.getPropertyPath());
		if(order != null)
			return false;
		return true;
	}


	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
