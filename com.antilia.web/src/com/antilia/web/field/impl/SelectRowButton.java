/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.lang.PropertyResolver;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.util.OkDialogButton;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SelectRowButton<B extends Serializable> extends OkDialogButton {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	private IFieldModel<B> fieldModel;
	private ISelectionField selectionField;
	
	private B bean;
	
	/**
	 * @param id
	 * @param dialog
	 */
	public SelectRowButton(String id, DefaultDialog dialog, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel, ISelectionField selectionField, B bean) {
		super(id, dialog);
		this.beanProxy = beanProxy;
		this.fieldModel = fieldModel;
		this.selectionField = selectionField;
		this.bean = bean;		
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.util.OklDialogButton#onOk(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.form.Form)
	 */
	@Override
	protected void onOk(AjaxRequestTarget target, Form<?> form) {
		String propertyName = fieldModel.getPropertyPath();
		PropertyResolver.setValue(propertyName, beanProxy.getBean(), bean, null);
		if(target != null) {
			target.addComponent((Component) selectionField);
		}
	}
	
	@Override
	protected String getLabel() {
		return null;
	}
	
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_BACK;
	}
}
