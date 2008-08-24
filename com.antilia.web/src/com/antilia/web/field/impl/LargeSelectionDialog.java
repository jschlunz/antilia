/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.common.util.ReflectionUtils;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LargeSelectionDialog<B extends Serializable> extends DefaultDialog {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IFieldModel<B> fieldModel;

	private BeanProxy<B> beanProxy;
	
		/**
	 * @param id
	 * @param button
	 */
	public LargeSelectionDialog(String id, DialogButton button, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel) {
		super(id, button);
		this.beanProxy = beanProxy;
		this.fieldModel = fieldModel;
		setModal(true);
		setWidth(800);
		setHeight(400);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#createBody(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Component createBody(String id) {		
		return new DialogSelectionCRUDPanel(id, ReflectionUtils.getPropertyClass(beanProxy.getBeanClass(), fieldModel.getPropertyPath()), this);
	}

}
