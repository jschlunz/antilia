/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web;

import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.apache.wicket.resource.loader.ComponentStringResourceLoader;

import com.antilia.web.resources.PackageResourceLoader;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class WebApplication extends org.apache.wicket.protocol.http.WebApplication {

	/**
	 * 
	 */
	public WebApplication() {
		super();
	}
	
	@Override
	protected void init() {
		super.init();
		
		getResourceSettings().addStringResourceLoader(
				new ComponentStringResourceLoader());
		getResourceSettings().addStringResourceLoader(
				new ClassStringResourceLoader(this.getClass()));

		// Adding our custom StringResourceLoaders
		getResourceSettings().addStringResourceLoader(PackageResourceLoader.getInstance());
		
	}
}
