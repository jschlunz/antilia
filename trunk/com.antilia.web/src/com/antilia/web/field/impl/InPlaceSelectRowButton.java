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

import com.antilia.web.button.AbstractButton;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InPlaceSelectRowButton<B extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	private IFieldModel<B> fieldModel;
	
	private B bean;
	
	private CRUDPanel< Serializable> parent;
	
	/**
	 * @param id
	 * @param dialog
	 */
	public InPlaceSelectRowButton(String id,  CRUDPanel< Serializable> parent, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel, B bean) {
		super(id, true);
		this.parent = parent;
		this.beanProxy = beanProxy;
		this.fieldModel = fieldModel;
		this.bean = bean;		
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		String propertyName = fieldModel.getPropertyPath();
		PropertyResolver.setValue(propertyName, beanProxy.getBean(), bean, null);
		IDialogScope dialogScope = findDialogScope();
		dialogScope.replaceBody(parent);
		if(target != null) {
			target.addComponent((Component) dialogScope);
		}
	}	
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	private IDialogScope  findDialogScope() {
		return (IDialogScope)findParent(IDialogScope.class);
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
