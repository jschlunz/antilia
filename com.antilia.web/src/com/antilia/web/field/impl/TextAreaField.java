/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;

import com.antilia.web.field.IFieldModel;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TextAreaField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	private org.apache.wicket.markup.html.form.TextArea<B> textArea;
	
	
	/**
	 * @param id
	 * @param beanClass
	 * @param propertyPath
	 */
	public TextAreaField(String id, IFieldModel<B> model) {
		super(id, model);
		label = new Label("label", getLabelModel());
		add(label);
				
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		if(textArea == null) {
			textArea = new org.apache.wicket.markup.html.form.TextArea<B>(
				"field", 
				getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
			add(textArea);
		}		
		super.onBeforeRender();
	}
}
