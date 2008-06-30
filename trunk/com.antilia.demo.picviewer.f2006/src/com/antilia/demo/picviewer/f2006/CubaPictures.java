/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.f2006;

import com.antilia.demo.picviewer.osgi.PackagedPicturesSource;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CubaPictures extends PackagedPicturesSource {

	private static final long serialVersionUID = 1L;

	/**
	 * @param files
	 */
	public CubaPictures() {
		super("Catedral_Habana.JPG",
				"Escambray.JPG",
				"Iglesia_y_Caballos.jpg",
				"Morro_Habana.JPG",
				"Vinyales.JPG");
	}

	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesSource#getTitle()
	 */
	public String getTitle() {
		return "Summer 2006 Cuba";
	}

}
