/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.palette;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MyPalette extends Palette {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param choicesModel
	 * @param choiceRenderer
	 * @param rows
	 * @param allowOrder
	 */
	public MyPalette(String id, IModel choicesModel,
			IChoiceRenderer choiceRenderer, int rows, boolean allowOrder) {
		super(id, choicesModel, choiceRenderer, rows, allowOrder);
	}

	/**
	 * @param id
	 * @param model
	 * @param choicesModel
	 * @param choiceRenderer
	 * @param rows
	 * @param allowOrder
	 */
	public MyPalette(String id, IModel model, IModel choicesModel,
			IChoiceRenderer choiceRenderer, int rows, boolean allowOrder) {
		super(id, model, choicesModel, choiceRenderer, rows, allowOrder);
	}
	
	@Override
	protected ResourceReference getCSS() {
		return DefaultStyle.CSS_PALETTE;
	}

}
