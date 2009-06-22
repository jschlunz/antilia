/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.wicketstuff.minis.veil.VeilResources;

import com.antilia.common.query.IOrder;
import com.antilia.common.query.IQuery;
import com.antilia.common.query.Order;
import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SortColumnButton<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private IColumnModel<E> columnModel;
	
	private IDialogScope dialogScope;
	
	/**
	 * @param id
	 */
	public SortColumnButton(String id, IColumnModel<E> columnModel) {
		super(id);
	
		this.columnModel = columnModel;
		
		AjaxButton ascending =  new AjaxButton("ascending") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SortColumnButton.this.onAscendingSubmit(target, form);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return SortColumnButton.this.getAjaxCallDecorator();
			}
			
			@Override
			public boolean isEnabled() {
				return SortColumnButton.this.isEnabled();
			}
			
		};			
		add(ascending);
	
		Image image = new Image("image", DefaultStyle.IMG_SORT_ASC_SMALL);		
		ascending.add(image);
		
		AjaxButton descending =  new AjaxButton("descending") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SortColumnButton.this.onDescendingSubmit(target, form);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return SortColumnButton.this.getAjaxCallDecorator();
			}
			
			
			
			@Override
			public boolean isEnabled() {
				return SortColumnButton.this.isEnabled();
			}
			
		};			
		add(descending);
	
		image = new Image("image", DefaultStyle.IMG_SORT_DES_SMALL);		
		descending.add(image);
	}

	protected IAjaxCallDecorator getAjaxCallDecorator()
	{
		return new IAjaxCallDecorator() {
			
			private static final long serialVersionUID = 1L;

			public CharSequence decorateOnFailureScript(CharSequence script) {
				IDialogScope dialogScope = getDialogScope();
				if(dialogScope != null) {
					return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";" ;
				}
				return script;
			}
			
			public CharSequence decorateOnSuccessScript(CharSequence script) {
				IDialogScope dialogScope = getDialogScope();
				if(dialogScope != null) {
					return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";" ;
				}
				return script;
			}
			
			public CharSequence decorateScript(CharSequence script) {
				IDialogScope dialogScope = getDialogScope();
				if(dialogScope != null) {
					return VeilResources.Javascript.Generic.show(dialogScope.getDialogId()) + ";" + script;
				}
				return script;
			}
		};
	}
	
	public IDialogScope getDialogScope() {
		if(dialogScope == null)
			dialogScope = findParentDialog();
		return dialogScope;
	}
	
	private IDialogScope findParentDialog() {
		return (IDialogScope)findParent(IDialogScope.class);
	}
	
	/**
	 * Callback method for Ajax buttons.
	 * 
	 * @param target
	 * @param form
	 */
	protected void onAscendingSubmit(AjaxRequestTarget target, Form<?> form) {
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableNavidator().getQuery();
		IOrder<E> order = Order.asc(this.columnModel.getPropertyPath());
		query.clearOrders();
		query.addOrder(order);
		component.getPageableNavidator().reset();
		target.addComponent((Component)component);
	}
	
	@Override
	public boolean isVisible() {
		if(!this.columnModel.isSortable()) {
			return false;
		}
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableNavidator().getQuery();
		IOrder<E> order = query.getOrder(columnModel.getPropertyPath());
		if(order == null)
			return true;
		return false;
	}
	
	/**
	 * Callback method for Ajax buttons.
	 * 
	 * @param target
	 * @param form
	 */
	protected void onDescendingSubmit(AjaxRequestTarget target, Form<?> form) {
		IPageableComponent<E> component = findPageableComponent();
		IQuery<E> query = component.getPageableNavidator().getQuery();
		query.clearOrders();
		IOrder<E> order = Order.des(this.columnModel.getPropertyPath());		
		query.addOrder(order);
		component.getPageableNavidator().reset();
		target.addComponent((Component)component);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.IMenuItem#getOrder()
	 */
	public int getOrder() {
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
