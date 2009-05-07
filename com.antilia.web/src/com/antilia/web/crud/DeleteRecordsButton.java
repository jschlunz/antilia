/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.util.OkDialogButton;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DeleteRecordsButton<E extends Serializable> extends OkDialogButton {

	private static final long serialVersionUID = 1L;

	public DeleteRecordsButton(String id, DefaultDialog dialog) {
		super(id, dialog);
	}
		
	@Override
	protected void onOk(AjaxRequestTarget target, Form<?> form) {
		CRUDPanel<E> crudPanel = getCRUDPanel();
		if(crudPanel != null && crudPanel.getSelected().getSelected().size() > 0) {			
			crudPanel.getSearchPanel().getPageableProvider().removeAll(crudPanel.getSelected().getSelected());
			crudPanel.getSearchPanel().getPageableProvider().reset();
			crudPanel.getSelected().clear();			
			target.addComponent((Component)crudPanel);			
		}
	}
	
	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		CRUDPanel<E> crudPanel = getCRUDPanel();
		target.addComponent(crudPanel.getSearchPanel().getFeedback());
	}
	
	@SuppressWarnings("unchecked")
	public CRUDPanel<E> getCRUDPanel() {
		return (CRUDPanel<E> )findParent(CRUDPanel.class);
	}
	
	@Override
	protected String getLabelKey() {
		return "DeleteRecordsButton.label";
	}
	
}
