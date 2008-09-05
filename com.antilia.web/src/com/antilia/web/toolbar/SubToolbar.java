/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.toolbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class   SubToolbar extends Panel implements IToolbarItem, IToolbar {

	private static final long serialVersionUID = 1L;

	private IToolbar parentToolbar;
	
	List<IToolbarItem> items = new ArrayList<IToolbarItem>();
	
	private Image image;
	/**
	 * @param id
	 */
	public SubToolbar(String id, IToolbar parentToolbar) {
		super(id);
		
		this.parentToolbar = parentToolbar;
		
		image = newImage("image");
		
		WebMarkupContainer link = new WebMarkupContainer("link");
		link.add(new AttributeModifier("class", new Model<String>() {			
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				if(SubToolbar.this.parentToolbar.isOnTop())
					sb.append("item1");
				else 
					sb.append("item2");
				if(image != null) 
					sb.append(" arrow");
				return sb.toString();
			}			
		}));
		link.add(image);
		
		Label text = newLabel("text");
		
		link.add(text);
		
		add(link);						
	}
	
	protected Label newLabel(String id) {
			return new Label(id, getTitle());
	}
	
	protected abstract String getTitle();
	
	protected Image newImage(String id) {
		return new Image(id, DefaultStyle.IMG_TOOLBAR_ARROW ) {
			@Override
			public boolean isVisible() {
				return !SubToolbar.this.parentToolbar.isOnTop();
			}
		};		
	}


	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		RefreshingView items = new RefreshingView("items") {
			@Override
			protected Iterator getItemModels() {
				List<IModel> list = new ArrayList<IModel>();
				int i = 0;
				for(IToolbarItem item: SubToolbar.this.items) {			
					if(item instanceof Component) {
						list.add(new Model(new Integer(i)));
						i++;
					}
				}
				return list.iterator();
			}
			
			@Override
			protected void populateItem(Item item) {
				RepeatingView rowView = new RepeatingView("item");
				item.add(rowView);
				int index = (Integer)item.getModel().getObject();
				rowView.add((Component)SubToolbar.this.items.get(index));
				
			}
		};		
		addOrReplace(items);

		/*
		for(IToolbarItem item: this.items) {			
			if(item instanceof Component) {
				RepeatingView itemView = new RepeatingView(items.newChildId());				
				items.add(itemView);
				itemView.add((Component)item);
			}
		}
		*/
		
	}
	
	public IToolbar addItem(IToolbarItem item) {
		items.add(item);
		return this;
	}
	
	@Override
	public IToolbar removeItem(IToolbarItem item) {
		items.add(item);
		return this;
	}
	
	/**
	 * @return the parentToolbar
	 */
	public IToolbar getParentToolbar() {
		return parentToolbar;
	}

	/**
	 * @param parentToolbar the parentToolbar to set
	 */
	public void setParentToolbar(IToolbar parentToolbar) {
		this.parentToolbar = parentToolbar;
	}

	@Override
	public boolean isOnTop() {
		return false;
	}
}
