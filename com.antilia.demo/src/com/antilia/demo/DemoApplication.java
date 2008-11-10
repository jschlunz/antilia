/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo;

import com.antilia.web.AntiliaWebApplication;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DemoApplication extends AntiliaWebApplication {

	/**
	 * 
	 */
	public DemoApplication() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		getMarkupSettings().setStripWicketTags(true);
		getDebugSettings().setAjaxDebugModeEnabled(true);
		
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
