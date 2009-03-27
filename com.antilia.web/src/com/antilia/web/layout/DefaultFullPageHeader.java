/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.web.layout;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DefaultFullPageHeader extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public DefaultFullPageHeader(String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param model
	 */
	public DefaultFullPageHeader(String id, IModel<?> model) {
		super(id, model);
	}

}
