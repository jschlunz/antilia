/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ExportPanel(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param model
	 */
	public ExportPanel(String id, IModel model) {
		super(id, model);
	}

}
