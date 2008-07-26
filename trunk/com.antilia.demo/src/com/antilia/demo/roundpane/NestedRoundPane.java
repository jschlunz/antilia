/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.roundpane;

import org.apache.wicket.markup.repeater.RepeatingView;

import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class NestedRoundPane extends RoundPane {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public NestedRoundPane(String id, String title, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		RepeatingView repeatingView = new RepeatingView("test");				
		NestedPanel roundPane = new NestedPanel(repeatingView.newChildId(), "Neted 1", new OrangeStyle());
		repeatingView.add(roundPane);
		
		NestedPanel roundPane2 = new NestedPanel(repeatingView.newChildId(), "Neted 2", new BlueStyle());
		repeatingView.add(roundPane2);
		
		addToBody(repeatingView);
	}

}
