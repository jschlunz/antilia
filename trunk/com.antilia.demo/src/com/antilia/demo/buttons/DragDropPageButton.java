/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.buttons;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.demo.Index;
import com.antilia.demo.dialogs.DialogsPanel;
import com.antilia.demo.dragdrop.DragDropPanel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DragDropPageButton extends AppNavigationButton {

	private static final long serialVersionUID = 1L;

	/**
	 * @param page
	 */
	public DragDropPageButton(Index page) {
		super("dragDrop",page);
	}

	/* (non-Javadoc)
	 * @see com.antilia.test.AppNavigationButton#getBodyPanel(java.lang.String)
	 */
	@Override
	public WebMarkupContainer getBodyPanel(String id) {
		return new DragDropPanel(id);
	}
	
	@Override
	protected String getLabel() {
		return "Drag-Drop";
	}

}
