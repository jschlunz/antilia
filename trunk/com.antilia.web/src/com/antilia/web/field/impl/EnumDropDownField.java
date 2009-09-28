/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;


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
	public EnumDropDownField(String id, IFieldModel<B> model,FieldMode mode) {
		super(id, model, mode);
		label = new Label("label", getLabelModel());
		add(label);
				
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		if(textField == null) {			
			Class<Enum> enumClass = (Class<Enum>)getFieldModel().getFieldClass();
			textField = new EnumDropDownChoice("field", enumClass, getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
			textField.setOutputMarkupId(true);
			if(getMode() == FieldMode.EDIT && getFieldModel().isRequiered()) {
				textField.setRequired(true);
				textField.add(new AttributeModifier("class",new Model<String>("requiredText")));
				textField.setLabel(getLabelModel());
			}
			add(textField);
		}		
		super.onBeforeRender();
	}
	
	protected IModel<String> getLabelModel() {
		String propertyPath = getFieldModel().getPropertyPath();
		String key = ResourceUtils.getPropertyResourceKey(getFieldModel().getBeanClass(), propertyPath);
		return new StringResourceModel(key, this, null, propertyPath);
		
	}


	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
	}
}
