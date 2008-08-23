/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;

import com.antilia.web.field.IFieldModel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LargeSelectionField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	private org.apache.wicket.markup.html.form.TextField textField;
	
	/**
	 * @param id
	 * @param model
	 */
	public LargeSelectionField(String id, IFieldModel<B> model) {
		super(id, model);
		
		label = new Label("label", getLabelModel());
		add(label);
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		if(textField == null) {
			textField = new org.apache.wicket.markup.html.form.TextField(
				"field", 
				getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
			add(textField);
		}		
		
		add(new LargeSelectionButton<B>("selectionPanel", getBeanProxy(), getFieldModel()));
	}

}
