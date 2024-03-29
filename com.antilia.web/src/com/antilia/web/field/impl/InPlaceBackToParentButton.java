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

import com.antilia.web.button.AbstractButton;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.layout.IScopedPanel;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InPlaceBackToParentButton<B extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;
	
	private CRUDPanel< Serializable> parent;
	
	/**
	 * @param id
	 * @param dialog
	 */
	public InPlaceBackToParentButton(String id,  CRUDPanel< Serializable> parent) {
		super(id, true);
		this.parent = parent;
	}

	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		IScopedPanel dialogScope = findScopedPanel();
		dialogScope.replaceBody(parent);
		if(target != null) {
			target.addComponent((Component) dialogScope);
		}
	}	
	
	private IScopedPanel  findScopedPanel() {
		return (IScopedPanel)findParent(IScopedPanel.class);
	}
	
	@Override
	protected String getLabel() {
		return "Go back...";
	}
	
	@Override
	protected String getLabelKey() {
		return "InPlaceBackToParentButton.label";
	}
	
	@Override
	protected String getTitleKey() {
		return "InPlaceBackToParentButton.label";
	}
	
	
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_BACK;
	}
}
