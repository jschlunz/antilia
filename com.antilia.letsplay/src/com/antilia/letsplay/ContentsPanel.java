/**
 * 
 */
package com.antilia.letsplay;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author reiern70@gmail.com
 *
 */
public  class ContentsPanel extends RoundPane {

	private static final long serialVersionUID = 1L;

	public ContentsPanel(String id, RoundPaneStyle boxStyle) {
		this(id, "", boxStyle);
	}
	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public ContentsPanel(String id, String title, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		addToBody(newContentPanel("content"));
		setWidth(860);
		setHeight(800);
		setCentered(false);
	}
	
	protected Panel newContentPanel(String id) {
		return new EmptyPanel(id);
	}
	
}
