/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008.
 */
package com.antilia.wstarter.demo;

import org.apache.wicket.protocol.http.WebApplication;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DemoApplication extends WebApplication {

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
