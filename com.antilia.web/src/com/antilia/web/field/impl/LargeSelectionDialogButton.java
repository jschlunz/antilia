/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogLink;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LargeSelectionDialogButton<B extends Serializable> extends DialogLink {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	
	private IFieldModel<B> fieldModel;
	
	/**
	 * @param id
	 */
	public LargeSelectionDialogButton(String id, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel) {
		super(id);
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
	
	@Override
	protected String getLabelKey() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		return new LargeSelectionDialog<B>(id, this, beanProxy, fieldModel);
	}

}
