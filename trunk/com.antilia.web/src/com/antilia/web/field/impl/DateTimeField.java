/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.PropertyValue;
import com.antilia.web.field.factory.FieldMode;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DateTimeField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	private AntiliaDateTimeField textField;
	
	
	/**
	 * @param id
	 * @param beanClass
	 * @param propertyPath
	 */
	public DateTimeField(String id, IFieldModel<B> model, FieldMode mode) {
		super(id, model, mode);
		label = new Label("label", getLabelModel());
		add(label);		
		if(mode.equals(FieldMode.SEARCH) && model.getOperators().length > 0) {
			add(new OperatorsPanel<B>("operator", model));
		} else {
			add(new EmptyPanel("operator"));
		}		
	}
	

	@Override
	protected void onBeforeRender() {
		if(textField == null) {
			PropertyValue<Date> propertyValue = getBeanProxy().getPropertyValue(getPropertyPath());
			IModel<Date> model = propertyValue.getModel();
			textField = new AntiliaDateTimeField("field", model);			
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
