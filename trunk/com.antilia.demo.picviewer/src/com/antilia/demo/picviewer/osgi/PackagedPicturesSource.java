/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.osgi;

import com.antilia.common.osgi.Aggregator;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class PackagedPicturesSource extends Aggregator<IPicture> implements IPicturesSource {

	public PackagedPicturesSource(String... files) {
		super();
		init(files);
	}
	
	private void init(String... files) {
		if(files != null) {
			for(String file: files) {
				add(new PackagedPicture(this, file));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesSource#getId()
	 */
	public String getId() {
		return this.getClass().getName();
	}

	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesSource#getPictures()
	 */
	public Iterable<IPicture> getPictures() {
		return elements();
	}

}
