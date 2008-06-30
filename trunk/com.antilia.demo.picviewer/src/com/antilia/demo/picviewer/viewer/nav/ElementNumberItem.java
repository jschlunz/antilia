/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.viewer.nav;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ElementNumberItem<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	/**
	 * @param id
	 */
	public ElementNumberItem() {
		super("elementnumber");		
		add(new Label("page", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				IPageableProvider<E> source = findPageableComponent().getPageableProvider();
				if(source.isEmpty())
					return 0;
				return source.currentIndex()+1;
			}
		}));
		
		add(new Label("npages", new Model() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				IPageableProvider<E> source = findPageableComponent().getPageableProvider();
				if(source.isEmpty())
					return 0;
				return source.size();
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
