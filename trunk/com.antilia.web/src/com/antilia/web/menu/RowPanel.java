/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.antilia.web.button.IMenuItem;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RowPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public RowPanel(String id, IMenuItem menuItem) {
		super(id);
		
		RepeatingView item = new RepeatingView("item");			
		item.add((Component)menuItem);				
		add(item);
		
	}
}
