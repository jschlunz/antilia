/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export;

import com.antilia.web.osgi.MenuFactoryServiceTracker;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportMenuFactoryServiceTracker extends MenuFactoryServiceTracker {

	/**
	 * 
	 */
	public ExportMenuFactoryServiceTracker() {
		super();
		
		add(new ExportPopulator());
	}

}
