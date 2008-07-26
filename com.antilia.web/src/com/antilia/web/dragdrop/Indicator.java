package com.antilia.web.dragdrop;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Indicator panel.
 */
public class Indicator extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Construct.
	 */
	public Indicator() {
		super("indicator");

		add(new Image("indicatorImage", "indicator.gif"));
		add(new Label("indicatorLabel", "Processing..."));
	}

}
