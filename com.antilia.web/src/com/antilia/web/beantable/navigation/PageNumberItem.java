/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.navigator.IPageableNavigator;
import com.antilia.web.toolbar.IToolbarItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PageNumberItem<E extends Serializable> extends Panel implements IMenuItem, IToolbarItem {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	/**
	 * @param id
	 */
	public PageNumberItem() {
		super("pagenumber");		
		add(new Label("page", new Model<Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				IPageableComponent< E> component = findPageableComponent();
				if(component  == null)
					return 0;
				IPageableNavigator<E> source = component.getPageableNavigator();
				if(source.isEmpty())
					return 1;
				return (source.currentPageNumber()+1);
			}
		}));
		
		add(new Label("npages", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				IPageableComponent< E> component = findPageableComponent();
				if(component  == null)
					return 0;
				IPageableNavigator<E> source = component.getPageableNavigator();
				if(source.isEmpty())
					return 1;
				return source.getNumberOfPages();
			}
		}));
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
