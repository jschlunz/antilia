/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.osgi;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IPicturesService {

	/**
	 * 
	 * @param source
	 * @return
	 */
	IPicturesService addPicturesSource( IPicturesSource  source);
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	IPicturesService removePicturesSource( IPicturesSource  source);
	
	/**
	 * 
	 * @return
	 */
	Iterable<IPicturesSource> getSources();
	
}
