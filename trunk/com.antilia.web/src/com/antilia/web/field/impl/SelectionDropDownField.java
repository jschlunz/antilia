/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SelectionDropDownField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	private DropDownChoice<B> textField;
	
	/**
	 * @param id
	 * @param beanClass
	 * @param propertyPath
	 */
	public SelectionDropDownField(String id, IFieldModel<B> model, FieldMode mode) {
		super(id, model, mode);
		label = new Label("label", getLabelModel());
		add(label);
				
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		if(textField == null) {
			Class<B> beansClass = (Class<B>)getFieldModel().getFieldClass();
			textField = new EntityDownChoice("field", beansClass, getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
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
