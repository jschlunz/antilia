/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.letsplay.components;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.letsplay.resources.AppStyle;
import com.antilia.web.button.AbstractLink;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PreviousWordLink extends AbstractLink {

	private static final long serialVersionUID = 1L;

	public PreviousWordLink(String id) {
		super(id);
	}
	
	@Override
	protected void onClick(AjaxRequestTarget target) {
		ScrambledWordPanel panel = findParent(ScrambledWordPanel.class);
		panel.previous(target);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {		
		return AppStyle.IMG_PREVIOUS;
	}
	
	@Override
	protected ResourceReference getDisabledImage() {
		return AppStyle.IMG_PREVIOUS;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "";
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
		
}
