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
public  class DropDownMenu extends Panel implements IMenuItem, IMenuItemHolder {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	RepeatingView toolBar;
	

	private String verticalStyleClass = "vertical-menu";
	
	private String menuStyle = "border: 1px solid #edf1f9; overflow: visible;";
	
	private IMenuItemsFactory[] factories;	
	
	/**
	 * @param id
	 */
	public DropDownMenu(String id, IMenuItemsFactory... factories) {
		super(id);
		
		add(HeaderContributor.forCss(DefaultStyle.CSS_MAIN));	
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		
		setOutputMarkupId(true);
		this.factories = factories;
		
		toolBar = new RepeatingView("toolbar");	
		
		WebMarkupContainer mainDiv = new WebMarkupContainer("mainDiv");		
		mainDiv.add(new AttributeModifier("class", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DropDownMenu.this.getVerticalStyleClass();
							
			}
		}));
		mainDiv.add(new AttributeModifier("style", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return menuStyle;							
			}
		}));
		add(mainDiv);
		// close button 
		
		mainDiv.add( new CloseDropDownButton("close"));
		
		WebMarkupContainer menu = new WebMarkupContainer("menu");
		populateMenuItems(id);
		menu.add(toolBar);		
		mainDiv.add(menu);
	}	
	
	
	protected String getVerticalStyleClass() {
		return verticalStyleClass;
	}
	
	public IMenuItemHolder addMenuItem(IMenuItem menuItem) {
		if(menuItem instanceof Component) {			
			RowPanel item =  new RowPanel(toolBar.newChildId(), menuItem);			
			toolBar.add(item);
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
		
	public void setVerticalStyleClass(String verticalStyleClass) {
		this.verticalStyleClass = verticalStyleClass;
	}
	
	public String getMenuStyle() {
		return menuStyle;
	}
	
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}
	
	@Override
	public String newItemId() {
		return toolBar.newChildId();
	}
}
