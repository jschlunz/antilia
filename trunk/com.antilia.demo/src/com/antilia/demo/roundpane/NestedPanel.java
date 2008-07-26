/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.roundpane;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class NestedPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public NestedPanel(String id,  String title, RoundPaneStyle style) {
		super(id);
		
		LatinRoundPane roundPane = new LatinRoundPane("pane", title, style);
		roundPane.setResizable(true);
		add(roundPane);
	}

}
