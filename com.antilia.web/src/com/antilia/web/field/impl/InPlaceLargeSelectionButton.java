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

import com.antilia.common.util.ReflectionUtils;
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
public class InPlaceLargeSelectionButton<B extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	
	private IFieldModel<B> fieldModel;
	
	/**
	 * @param id
	 */
	public InPlaceLargeSelectionButton(String id, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel) {
		super(id, true);
		this.beanProxy  = beanProxy;
		this.fieldModel = fieldModel;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_VIEW ;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		CRUDPanel<Serializable> crudPanel =findCRUDPanel();
		IDialogScope dialogScope = findDialogScope();		
		Class<B> beanClass = (Class<B>) ReflectionUtils.getPropertyClass(beanProxy.getBeanClass(), fieldModel.getPropertyPath());
		if(target != null) {
			InPlaceSelectionCRUDPanel<B> inPlaceSelectionCRUDPanel = new InPlaceSelectionCRUDPanel<B>(IDialogScope.BODY_ID, crudPanel, beanClass ,  beanProxy, fieldModel);
			dialogScope.replaceBody(inPlaceSelectionCRUDPanel);
			target.addComponent((Component)dialogScope);
		}
	}
	
	protected IDialogScope findDialogScope() {
		return (IDialogScope)findParent(IDialogScope.class);
	}
	
	@SuppressWarnings("unchecked")
	protected CRUDPanel<Serializable> findCRUDPanel() {
		return (CRUDPanel<Serializable>)findParent(CRUDPanel.class);
	}
}
