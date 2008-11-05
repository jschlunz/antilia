/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;

import com.antilia.web.field.IFieldModel;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EnumDropDownField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	private DropDownChoice<Enum> textField;
	
	/**
	 * @param id
	 * @param beanClass
	 * @param propertyPath
	 */
	public EnumDropDownField(String id, IFieldModel<B> model) {
		super(id, model);
		label = new Label("label", getLabelModel());
		add(label);
				
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		if(textField == null) {
			Class<Enum> enumClass = (Class<Enum>)getFieldModel().getFieldClass();
			textField = new EnumDropDownChoice("field", enumClass, getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
			add(textField);
		}		
		super.onBeforeRender();
	}


	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
	}
}
