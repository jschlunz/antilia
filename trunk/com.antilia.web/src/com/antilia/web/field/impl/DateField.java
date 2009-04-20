/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.Model;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DateField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	private DateTextField textField;
	
	
	/**
	 * @param id
	 * @param beanClass
	 * @param propertyPath
	 */
	public DateField(String id, IFieldModel<B> model, FieldMode mode) {
		super(id, model, mode);
		label = new Label("label", getLabelModel());
		add(label);		
		if(mode.equals(FieldMode.SEARCH) && model.getOperators().length > 0) {
			add(new OperatorsPanel<B>("operator", model));
		} else {
			add(new EmptyPanel("operator"));
		}		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		if(textField == null) {
			textField = new DateTextField(
				"field", 
				getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
			textField.add(new AntiliaDatePicker());
			add(textField);
			textField.setLabel(getLabelModel());
			if(getMode() == FieldMode.EDIT && getFieldModel().isRequiered()) {
				textField.setRequired(true);
				textField.add(new AttributeModifier("class",new Model<String>("requiredText")));
			}
		}		
		super.onBeforeRender();
	}
}
