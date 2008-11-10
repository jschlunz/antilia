/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer;

import com.antilia.web.AntiliaWebApplication;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PicViewerApplication extends AntiliaWebApplication {

	/**
	 * 
	 */
	public PicViewerApplication() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		getMarkupSettings().setStripWicketTags(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		
	}


	/* (non-Javadoc)
	 * @see wicket.Application#getHomePage()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class getHomePage() {
		return Index.class;
	}
}
