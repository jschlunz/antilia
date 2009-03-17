/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.mygosumenu.xul;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.antilia.web.button.IMenuItem;
import com.antilia.web.button.IMenuItemHolder;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class XulHorizontalMenu extends Panel implements IMenuItemHolder {
	
	private static final long serialVersionUID = 1L;

	List<IMenuItem> menuItems = new ArrayList<IMenuItem>();
	
	public static final ResourceReference CSS  = new ResourceReference(XulHorizontalMenu.class, "horizontal.css");	
	public static final ResourceReference JS  = new ResourceReference(XulHorizontalMenu.class, "XulMenu.js");
	
	private RepeatingView items;
	
	private WebMarkupContainer menu;
	
	public XulHorizontalMenu(String id) {
		super(id);
		
		menu = new WebMarkupContainer("menu");
		
		add(menu);
		
		add(HeaderContributor.forCss(CSS));
		add(HeaderContributor.forJavaScript(JS));
		
		items = new RepeatingView("items");
		add(items);
	}
	
	
	@Override
	protected void onBeforeRender() {
		poupulateMenu(items);
		super.onBeforeRender();
	}
	
	protected  void poupulateMenu(RepeatingView items) {
		items.removeAll();
		for(IMenuItem item: menuItems) {
			if(item instanceof Component) {
				items.add((Component)item);
			}
		}
	}


	public IMenuItemHolder addMenuItem(IMenuItem menuItem) {
		menuItems.add(menuItem);
		return this;
	}

	public String newItemId() {
		return items.newChildId();
	}	
}
