/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import org.apache.wicket.ResourceReference;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DropSelectionButton extends AbstractButton {

	
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param ajaxButton
	 */
	public DropSelectionButton(String id) {
		super(id, true);
	}

	
	@Override
	public void onSubmit() {
		
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
	
	

}
