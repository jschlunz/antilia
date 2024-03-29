/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.query.Operator;
import com.antilia.common.util.ResourceUtils;
import com.antilia.web.field.BeanForm;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class BaseFormField<B extends Serializable> extends Panel implements IFormField {

	private static final long serialVersionUID = 1L;

	private String propertyPath;
	
	private Operator selectedOperator;
	
	private Operator[] operators;
	
	private IFieldModel<B> fieldModel;
	
	protected Label label;
	
	private FieldMode mode;
	
	/**
	 * @param id
	 */
	public BaseFormField(String id, IFieldModel<B> model,FieldMode mode) {
		super(id);	
		this.fieldModel = model;
		this.mode = mode;
		this.propertyPath = model.getPropertyPath();
		this.selectedOperator = model.getSelectedOperator();
		this.operators = model.getOperators();		
	}

	public String getPropertyPath() {
		return propertyPath;
	}

	protected IModel<String> getLabelModel() {
		String propertyPath = getFieldModel().getPropertyPath();
		String key = ResourceUtils.getPropertyResourceKey(getFieldModel().getBeanClass(), propertyPath);
		return new StringResourceModel(key, this, null, propertyPath);
		
	}
	
	public void setPropertyPath(String property) {
		this.propertyPath = property;
	}
	
	@SuppressWarnings("unchecked")
	protected BeanForm<B> findBeanForm() {
		return (BeanForm<B>)findParent(BeanForm.class);
	}
	
	protected BeanProxy<B> getBeanProxy() {
		BeanForm<B> beanForm = findBeanForm();
		if(beanForm != null)
			return beanForm.getBeanProxy();
		return null;
	}
	

	public Operator getSelectedOperator() {
		return selectedOperator;
	}

	public void setSelectedOperator(Operator selectedOperator) {
		this.selectedOperator = selectedOperator;
	}

	public Operator[] getOperators() {
		return operators;
	}

	public void setOperators(Operator[] operators) {
		this.operators = operators;
	}

	public IFieldModel<B> getFieldModel() {
		return fieldModel;
	}

	public void setFieldModel(IFieldModel<B> fieldModel) {
		this.fieldModel = fieldModel;
	}
	
	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
	}

	public FieldMode getMode() {
		return mode;
	}

	public void setMode(FieldMode mode) {
		this.mode = mode;
	}
}
