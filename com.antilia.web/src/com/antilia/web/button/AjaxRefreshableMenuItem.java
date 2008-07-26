/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AjaxRefreshableMenuItem extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private static String ITEM_ID = "item";
	
	private IMenuItem wrappedItem;
	
	/**
	 * @param id
	 */
	private AjaxRefreshableMenuItem(String id) {
		super(id);
	}
	
	public static AjaxRefreshableMenuItem createRefreshableMenuItem(String id, IMenuItem wrappedItem) {
		AjaxRefreshableMenuItem item = new AjaxRefreshableMenuItem(id);
		if(wrappedItem instanceof Component) {
			item.add((Component)wrappedItem);
			((Component)wrappedItem).setOutputMarkupId(true);
			item.setWrappedItem(wrappedItem);
		} else
			item.add(new Label(getItemId(), ""));
		return item;
	}

	public static String getItemId() {
		return ITEM_ID;
	}

	public int getOrder() {
		return 0;
	}

	public IMenuItem getWrappedItem() {
		return wrappedItem;
	}

	public void setWrappedItem(IMenuItem wrappedItem) {
		this.wrappedItem = wrappedItem;
	}
}
