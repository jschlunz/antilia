/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.Order;
import com.antilia.common.query.IOrder.OrderType;
import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SortAscendingButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private IColumnModel<E> columnModel;
	
	public SortAscendingButton(String id, IColumnModel<E> columnModel) {
		super(id, true);
		this.columnModel = columnModel;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_SORT_ASC;
	}
	
	@Override
	protected ResourceReference getDisabledImage() {
		return DefaultStyle.IMG_SORT_ASC;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	public boolean isVisible() {
		if(!this.columnModel.isSortable()) {
			return false;
		}
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableNavidator().getQuery();
		IOrder<E> order = query.getOrder(columnModel.getPropertyPath());
		if(order != null && order.getType().equals(OrderType.DESCENDING))
			return true;
		return false;
	}

	
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableNavidator().getQuery();
		IOrder<E> order = Order.asc(this.columnModel.getPropertyPath());
		query.clearOrders();
		query.addOrder(order);
		component.getPageableNavidator().reset();
		target.addComponent((Component)component);
	}
	
	
	@Override
	public void onSubmit() {
		
	}

	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
