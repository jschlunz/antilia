/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.PropertyValue;
import com.antilia.web.field.factory.FieldMode;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TextAreaField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	private org.apache.wicket.markup.html.form.TextArea<B> textArea;
	
	
	/**
	 * @param id
	 * @param beanClass
	 * @param propertyPath
	 */
	public TextAreaField(String id, IFieldModel<B> model, FieldMode mode) {
		super(id, model, mode);
		label = new Label("label", getLabelModel());
		add(label);
				
	}
	

	@Override
	protected void onBeforeRender() {
		if(textArea == null) {
			PropertyValue<B> propertyValue = getBeanProxy().getPropertyValue(getPropertyPath());
			IModel<B> model = propertyValue.getModel();			
			textArea = new org.apache.wicket.markup.html.form.TextArea<B>(
				"field", model);
			add(textArea);
			if(getMode() == FieldMode.EDIT && getFieldModel().isRequiered()) {
				textArea.setRequired(true);
				textArea.add(new AttributeModifier("class",new Model<String>("requiredText")));
			}
		}		
		super.onBeforeRender();
	}
}
