/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.osgi;

import java.io.Serializable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IPicturesSource extends Serializable {
	
	String getId();

	String getTitle();
	
	Iterable<IPicture> getPictures();
	
}
