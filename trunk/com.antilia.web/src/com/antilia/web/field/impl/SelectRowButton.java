/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.util.OklDialogButton;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.IFieldPanel;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SelectRowButton<B extends Serializable> extends OklDialogButton {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	private IFieldModel<B> fieldModel;
	private IFieldPanel fieldPanel;
	
	private B bean;
	
	/**
	 * @param id
	 * @param dialog
	 */
	public SelectRowButton(String id, DefaultDialog dialog, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel, IFieldPanel fieldPanel, B bean) {
		super(id, dialog);
		this.beanProxy = beanProxy;
		this.fieldModel = fieldModel;
		this.fieldPanel = fieldPanel;
		this.bean = bean;		
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.util.OklDialogButton#onOk(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.form.Form)
	 */
	@Override
	protected void onOk(AjaxRequestTarget target, Form form) {
		String path = fieldModel.getPropertyPath();
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
