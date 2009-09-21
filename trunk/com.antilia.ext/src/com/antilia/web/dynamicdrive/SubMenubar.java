/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dynamicdrive;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class   SubMenubar extends Panel implements IMenuItem, IMenu {

	private static final long serialVersionUID = 1L;

	private IMenu parentToolbar;
	
	List<IMenuItem> items = new ArrayList<IMenuItem>();
	
	private RepeatingView itemsView;
	
	/**
	 * @param id
	 */
	public SubMenubar(String id, IMenu parentToolbar) {
		super(id);
		
		this.parentToolbar = parentToolbar;
		
		WebMarkupContainer link = new WebMarkupContainer("link");
		
		Label text = newLabel("text");
		
		link.add(text);
		
		add(link);	
		
		parentToolbar.addItem(this);
		
		itemsView = new RepeatingView("items");		
		addOrReplace(itemsView);
	}
	
	protected Label newLabel(String id) {		
		return new Label(id, getTitleModel());
	}
	
	protected IModel<String> getTitleModel() {
		return new ResourceModel(getTitle(), getTitle());
	}
	
	protected abstract String getTitle();


	@Override
	protected void onBeforeRender() {		
		
		itemsView.removeAll();
		
		for(IMenuItem item: this.items) {
			if(item instanceof Component) {
				itemsView.add((Component)item);
			}
		}
		
		super.onBeforeRender();
	}
	
	public IMenu addItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	public IMenu removeItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	/**
	 * @return the parentToolbar
	 */
	public IMenu getParentToolbar() {
		return parentToolbar;
	}

	/**
	 * @param parentToolbar the parentToolbar to set
	 */
	public void setParentToolbar(IMenu parentToolbar) {
		this.parentToolbar = parentToolbar;
	}

	public boolean isOnTop() {
		return false;
	}
}
