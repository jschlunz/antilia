/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.progress;

import java.awt.Color;
import java.awt.Graphics2D;

import com.antilia.web.roundpane.ColorMapper;
import com.antilia.web.roundpane.TransparentImageResource;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ProgressImg extends TransparentImageResource {

	private static final long serialVersionUID = 1L;

	private Color borderColor;
	
	private int progress = 5;

	/**
	 * 
	 * @param color
	 */
	public ProgressImg(Color borderColor, boolean ie6) {
		super(202, 20, ie6?"gif":"png");
		this.borderColor = borderColor;
	}
	
	/**
	 * 
	 * @param color
	 */
	public ProgressImg(String color, boolean ie6) {
		this(ColorMapper.mapColor(color), ie6);
	}
	

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource#render(java.awt.Graphics2D)
	 */
	@Override
	protected boolean render(Graphics2D graphics) {
		graphics.setColor(getBorderColor());		
		graphics.fillRect(0, 0, 202, 20);
		graphics.setColor(Color.RED);
		graphics.fillRect(1, 1, progress*2, 18);
		graphics.setColor(Color.WHITE);
		graphics.drawString(progress+"%" , 91, 14);
		return true;
	}

	/**
	 * @return the borderColor
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor the borderColor to set
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;		
	}

}
