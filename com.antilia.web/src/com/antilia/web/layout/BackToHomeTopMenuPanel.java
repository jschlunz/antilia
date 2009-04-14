/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.web.layout;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import com.antilia.web.button.IMenuItemHolder;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class BackToHomeTopMenuPanel<B extends Serializable> extends TopMenuPanel<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public BackToHomeTopMenuPanel(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param model
	 */
	public BackToHomeTopMenuPanel(String id, IModel<?> model) {
		super(id, model);
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		itemHolder.addMenuItem(new HomeLink("home"));
	}
	
}
