/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.dialog.util.ConfirmationDialog;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DeleteConfirmationDialogButton<E extends Serializable> extends DialogButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public DeleteConfirmationDialogButton() {
		super("delete");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_DELETE;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Delete";
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		ConfirmationDialog confirmationDialog = new ConfirmationDialog(id, this, new ResourceModel("deleteCofirmMessage")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected AbstractButton newOkButton(String id) {
				return new DeleteRecordsButton<E>(id, this);
			}
			
		};
		return confirmationDialog;
	}
	
	@Override
	protected boolean showDialog(AjaxRequestTarget target, Form<?> form) {
		CRUDPanel<E> crudPanel = getCRUDPanel();
		if(crudPanel != null && crudPanel.getSelected().getSelected().size() > 0) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public CRUDPanel<E> getCRUDPanel() {
		return (CRUDPanel<E> )findParent(CRUDPanel.class);
	}

	@Override
	protected String getLabelKey() {
		return "DeleteConfirmationDialogButton.label";
	}
}
