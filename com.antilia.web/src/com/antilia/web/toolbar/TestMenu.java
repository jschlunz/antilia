/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.toolbar;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestMenu extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TestMenu(String id) {
		super(id);
		add(HeaderContributor.forCss(DefaultStyle.CC_menu));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_ie5));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_DropDownMenuX));
	}

}
