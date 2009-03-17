/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.resources.DefaultStyle;

/**
 * A Menu class
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public  class NMenu extends Panel implements IMenuItem, IMenuItemHolder {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	RepeatingView toolBar;
	
	private String horizontalStyleClass = "nav-menu";
		
	private String menuStyle = "";
	
	private IMenuItemsFactory[] factories;
	
	
	/**
	 * @param id
	 */
	public NMenu(String id,  IMenuItemsFactory... factories) {
		super(id);
		
		add(HeaderContributor.forCss(DefaultStyle.CSS_MAIN));	
		//add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		
		setOutputMarkupId(false);
		setRenderBodyOnly(true);
		
		this.factories = factories;
		
		toolBar = new RepeatingView("toolbar");	
		
		WebMarkupContainer menu = new WebMarkupContainer("menu");		
		menu.add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return NMenu.this.getHorizontalStyleClass();							
			}
		}));
		menu.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return menuStyle;							
			}
		}));
		add(menu);
		
		populateMenuItems(id);
		menu.add(toolBar);		
		add(menu);
	}	
	
	public String getHorizontalStyleClass() {
		return horizontalStyleClass;
	}
		
	public IMenuItemHolder addMenuItem(IMenuItem menuItem) {
		if(menuItem instanceof Component) {
			toolBar.add((Component)menuItem);
		}
		return this;
	}
	
	public void populateMenuItems(String menuId) {
		for(IMenuItemsFactory factory: factories) {
			factory.populateMenuItems(menuId, this);
		}
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
	
	public String getMenuStyle() {
		return menuStyle;
	}
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}
	
	public String newItemId() {
		return toolBar.newChildId();
	}
}
