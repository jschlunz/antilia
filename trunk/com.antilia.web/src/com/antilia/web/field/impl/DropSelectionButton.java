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
public class DropSelectionButton<B extends Serializable> extends AbstractButton {

	
	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy; 
	private IFieldModel<B> fieldModel; 
	private IFieldPanel fieldPanel;
	
	/**
	 * @param id
	 * @param ajaxButton
	 */
	public DropSelectionButton(String id, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel) {
		super(id, true);		
		this.beanProxy = beanProxy;
		this.fieldModel = fieldModel;
	}

	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		if(this.fieldPanel == null)
			this.fieldPanel = findFieldPanel();
		String propertyName = fieldModel.getPropertyPath();
		PropertyResolver.setValue(propertyName, beanProxy.getBean(), null, null);
		if(target != null) {
			target.addComponent((Component) fieldPanel);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_REMOVE;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}
	
	
	private IFieldPanel findFieldPanel() {
		return (IFieldPanel)findParent(IFieldPanel.class);
	}

}
