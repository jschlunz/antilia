/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AutoFieldCell extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public AutoFieldCell(String id) {
		super(id);
		addOrReplace(newLabel("label"));
		addOrReplace(newField("field"));
	}
	
	protected abstract Label newLabel(String id);
	
	protected abstract Component newField(String id);

}
