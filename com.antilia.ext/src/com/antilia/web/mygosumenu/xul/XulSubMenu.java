/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.mygosumenu.xul;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.antilia.web.button.IMenuItem;
import com.antilia.web.button.IMenuItemHolder;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class XulSubMenu extends Panel implements IMenuItem, IMenuItemHolder {

	private static final long serialVersionUID = 1L;

	List<IMenuItem> menuItems = new ArrayList<IMenuItem>();
	
	RepeatingView items;
	
	private String title;
	
	/**
	 * @param id
	 */
	public XulSubMenu(String id, String title) {
		super(id);
		if(title == null)
			throw new IllegalArgumentException("Title should be not null");		
		this.title = title;
		
		Label label = new Label("title", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return XulSubMenu.this.getTitle();
			}
		});
		
		add(label);
		
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
	
	@Override
	public IMenuItemHolder addMenuItem(IMenuItem menuItem) {
		menuItems.add(menuItem);
		return this;
	}

	@Override
	public String newItemId() {
		return items.newChildId();
	}	
	
	@Override
	public int getOrder() {
		return 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	

}
