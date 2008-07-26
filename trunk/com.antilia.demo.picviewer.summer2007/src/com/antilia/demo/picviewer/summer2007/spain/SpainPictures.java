/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.summer2007.spain;

import com.antilia.demo.picviewer.osgi.PackagedPicturesSource;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SpainPictures extends PackagedPicturesSource {

	private static final long serialVersionUID = 1L;

	/**
	 * @param files
	 */
	public SpainPictures() {
		super("Spain_barcelona.jpg",
				"Spain_barcelona2.jpg",
				"Spain_overview.jpg");
	}

	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesSource#getTitle()
	 */
	public String getTitle() {
		return "Summer 2007 Spain";
	}

}
