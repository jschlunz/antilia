/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.roundpane;

import org.apache.wicket.markup.html.basic.Label;

import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LatinRoundPane extends RoundPane {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public LatinRoundPane(String id, String title, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		addToBody(new Label("test", "Latin"));
	}

}
