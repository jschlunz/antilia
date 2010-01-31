/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008.
 */
package com.antilia.wstarter.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Index extends WebPage {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Index() {
		Label  label = new Label("message", "Hello World Equinox!");
		add(label);
	}
		
}
