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
public class PicturesService extends Aggregator<IPicturesSource> implements IPicturesService {

	
	private static PicturesService instance = new PicturesService();
	
	private PicturesService() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesService#addPicturesSource(com.antilia.demo.picviewer.osgi.IPicturesSource)
	 */
	public IPicturesService addPicturesSource(IPicturesSource source) {
		add(source);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesService#getSources()
	 */
	public Iterable<IPicturesSource> getSources() {
		return elements();
	}

	/* (non-Javadoc)
	 * @see com.antilia.demo.picviewer.osgi.IPicturesService#removePicturesSource(com.antilia.demo.picviewer.osgi.IPicturesSource)
	 */
	public IPicturesService removePicturesSource(IPicturesSource source) {
		delete(source);
		return this;
	}

	public static PicturesService getInstance() {
		return instance;
	}

}
