/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.view;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.picviewer.osgi.PackagedPicture;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
class PicturePanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public PicturePanel(String id, PackagedPicture picture) {
		super(id);
		add(new Image("image", picture.getContent()));
	}
}
