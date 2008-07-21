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

import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.query.IOrder;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Order;
import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SortDescendingButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;
	
	private String propertyPath;

	public SortDescendingButton(String id,String propertyPath) {
		super(id, true);
		if(StringUtils.isEmpty(propertyPath))
			throw new IllegalArgumentException("propertyPath should be non null");
		this.propertyPath = propertyPath;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_SORT_DES;
	}
	
	@Override
	protected ResourceReference getDisabledImage() {
		return DefaultStyle.IMG_SORT_DES;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Sort Desc";
	}
	
	@Override
	public boolean isEnabled() {
		//IPageableComponent<E> component = findPageableComponent();
		//return component.getPageableProvider().hasNextPage();
		return true;
	}
	
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableProvider().getQuery();
		query.clearOrders();
		IOrder<E> order = Order.des(this.propertyPath);		
		query.addOrder(order);
		component.getPageableProvider().reset();
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
