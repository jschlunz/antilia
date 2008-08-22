/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.toolbar.IToolbarItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PageSizeButton<E extends Serializable> extends Panel implements IMenuItem, IToolbarItem {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	private TextField textField;
	/**
	 * @param id
	 */
	public PageSizeButton() {
		super("pageSize");		
		textField  = new TextField("field",  new Model() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				IPageableComponent<E> pageableComponent = findPageableComponent();
				if(pageableComponent != null)
					return pageableComponent.getPageableProvider().getPageSize();
				return 10;
			}
			
			@Override
			public void setObject(Object object) {
				try {
					findPageableComponent().getPageableProvider().setPageSize(Integer.parseInt(object.toString()));
				} catch (Exception e) {
					findPageableComponent().getPageableProvider().setPageSize(10);
				}
			}			
		});
		add(textField);
	}
	
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
