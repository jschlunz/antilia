/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.veil;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.wicketstuff.minis.veil.VeilResources;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AntiliaVeilResource extends VeilResources {

	private static final long serialVersionUID = 1L;
	
	private static final ResourceReference JS = new JavascriptResourceReference(AntiliaVeilResource.class, "antilia-veil.js");

	private static final ResourceReference CSS = new ResourceReference(AntiliaVeilResource.class, "antilia-veil.css");

	/**
	 * 
	 */
	public AntiliaVeilResource() {		
	}

	public void renderHead(IHeaderResponse response)
	{
		response.renderJavascriptReference(JS);
		response.renderCSSReference(CSS);
	}
}
