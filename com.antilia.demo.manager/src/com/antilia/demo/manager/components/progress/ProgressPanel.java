/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager.components.progress;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.progress.HtmlProgressBar;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ProgressPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ProgressPanel(String id) {
		super(id);
		
		HtmlProgressBar progress = new HtmlProgressBar("progress", 80);
		add(progress);
	}
	
}
