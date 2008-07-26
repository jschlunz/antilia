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
public class BottomRightDragImg extends TransparentImageResource {

	private static final long serialVersionUID = 1L;

	private Color backgroundColor;

	private Color handleColor;
	
	/**
	 * 
	 * @param color
	 */
	public BottomRightDragImg(final Color backgroundColor, boolean ie6) {
		this(backgroundColor, Color.BLACK, ie6);		
	}
	
	public BottomRightDragImg(final Color backgroundColor, final Color handleColor, boolean ie6) {
		super(15, 15, ie6?"gif":"png");
		this.backgroundColor = backgroundColor;
		this.handleColor = handleColor;
	}
	
	/**
	 * 
	 * @param color
	 */
	public BottomRightDragImg(final String backgroundColor, boolean ie6) {
		this(backgroundColor, "#ffffff" ,ie6);
	}
	
	/**
	 * 
	 * @param color
	 */
	public BottomRightDragImg(final String backgroundColor, final String handleColor, boolean ie6) {
		this(ColorMapper.mapColor(backgroundColor), ColorMapper.mapColor(handleColor), ie6);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource#render(java.awt.Graphics2D)
	 */
	@Override
	protected boolean render(Graphics2D graphics) {
		graphics.setColor(getBackgroundColor());		
		graphics.fillArc(-15, -15, 30, 30, 270, 90);
		graphics.setColor(getHandleColor());
		graphics.drawLine(7, 12, 12, 7);
		graphics.drawLine(4, 12, 12, 4);
		graphics.drawLine(1, 12, 12, 1);		
		graphics.drawLine(1, 9, 9, 1);
		graphics.drawLine(1, 6, 6, 1);
		graphics.drawLine(1, 3, 3, 1);
		return true;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

	public Color getHandleColor() {
		return handleColor;
	}

	public void setHandleColor(Color handleColor) {
		this.handleColor = handleColor;
	}
}
