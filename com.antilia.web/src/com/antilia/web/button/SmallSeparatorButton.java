/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SmallSeparatorButton extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private static long counter = 0;
	
	public SmallSeparatorButton() {
		super("mseparator"+getCounter());
		add(new Image("image", getImage()));
	}
	
	private synchronized static long getCounter() {
		return counter++;
	}
	
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_transparent_16_16;
	}

	public int getOrder() {
		return AbstractButton.NO_ORDER;
	}

}
