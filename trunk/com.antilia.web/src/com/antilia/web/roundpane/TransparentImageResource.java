/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource;

import Acme.JPM.Encoders.GifEncoder;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class TransparentImageResource extends RenderedDynamicImageResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @param width
	 * @param height
	 */
	public TransparentImageResource(final int width, final int height) {
		super(width, height);
		setType(BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * @param width
	 * @param height
	 * @param format
	 */
	public TransparentImageResource(final int width, final int height, String format) {
		super(width, height, format);
		setType(BufferedImage.TYPE_INT_ARGB);
	}

	
	protected byte[] render() {
		while (true) {
			final BufferedImage image = new BufferedImage(getWidth(), getHeight(), getType());
			
			createTansparency(image.createGraphics());
			
			Graphics2D graphics = image.createGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			if (render(graphics)) {
				return toImageData(image);
			}
		}
	}

	@Override
	protected byte[] toImageData(BufferedImage image) {
		if(getFormat().equals("gif")) {
			// Create output stream
			final ByteArrayOutputStream out = new ByteArrayOutputStream();			
			try {
				GifEncoder  encoder = new GifEncoder(image, out, true);
				encoder.setColorModel(image.getColorModel());
				encoder.encode();
				// Return the image data
				return out.toByteArray();
			} catch (Exception e) {
				return null;
			}
		} 		
		else 
			return super.toImageData(image);
	}	
	
	protected boolean createTansparency(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D.Double rect = new Rectangle2D.Double(0,0,getWidth(),getHeight()); 
		graphics.fill(rect);
		return true;
	}
}
