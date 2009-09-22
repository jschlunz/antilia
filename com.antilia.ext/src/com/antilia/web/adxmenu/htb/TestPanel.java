/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.adxmenu.htb;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestPanel extends Panel {
	
	private static final long serialVersionUID = 1L;

	public static ResourceReference HTB_CSS = new ResourceReference(TestPanel.class, "htb.css");
	
	public TestPanel(String id) {
		super(id);
		
		setRenderBodyOnly(false);
		add(HeaderContributor.forCss(HTB_CSS));
	
	}
}
