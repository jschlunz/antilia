/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TopRightImg extends TransparentImageResource {

	private static final long serialVersionUID = 1L;

	private Color color;

	/**
	 * 
	 * @param color
	 */
	public TopRightImg(final Color color, boolean ie6) {
		super(18, 18, ie6?"gif":"png");
		this.color = color;
	}
	
	/**
	 * 
	 * @param color
	 */
	public TopRightImg(String color, boolean ie6) {
		this(ColorMapper.mapColor(color), ie6);
	}
	

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource#render(java.awt.Graphics2D)
	 */
	@Override
	protected boolean render(Graphics2D graphics) {
		graphics.setColor(getColor());		
		graphics.fillArc(-18, 0, 36, 36, 0, 90);
		return true;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
