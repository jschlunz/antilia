/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.summer2007.italy;

import com.antilia.demo.picviewer.osgi.PackagedPicturesSource;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ItalyPictures extends PackagedPicturesSource {

	private static final long serialVersionUID = 1L;

	/**
	 * @param files
	 */
	public ItalyPictures() {
		super("Italy_Rome_colosseum.jpg",
				"Italy_venetia.jpg",
				"Trevi_Fountain_Rome.jpg");
	}

	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesSource#getTitle()
	 */
	public String getTitle() {
		return "Summer 2007 Italy";
	}

}
