/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.buttons;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.demo.Index;
import com.antilia.demo.roundpane.RoundPanels;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class RoundBoxButton extends AppNavigationButton {

	private static final long serialVersionUID = 1L;

	/**
	 * @param page
	 */
	public RoundBoxButton(Index page) {
		super("roundboxes",page);
	}

	/* (non-Javadoc)
	 * @see com.antilia.test.AppNavigationButton#getBodyPanel(java.lang.String)
	 */
	@Override
	public WebMarkupContainer getBodyPanel(String id) {
		return new RoundPanels(id);
	}
	
	@Override
	protected String getLabel() {
		return "Round Panes";
	}

	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	protected String getTitleKey() {
		return null;
	}
}
