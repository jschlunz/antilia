/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.web.button.AbstractLink;
import com.antilia.web.dialog.ModalContainer;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDButton extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private String label;
	
	private Index page;
	
	private ModalContainer modalContainer;
	
	/**
	 * @param id
	 */
	public CRUDButton(String id, String label, Index page, ModalContainer modalContainer) {
		super(id);
		this.label = label;
		this.page = page;
		this.modalContainer = modalContainer;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_TRANSPARENT;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#getLabel()
	 */
	@Override
	protected String getLabel() {
		return this.label;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void onClick(AjaxRequestTarget target) {
		if(target != null) {
			this.page.getBody().addOrReplace(modalContainer);
			target.addComponent(this.page.getBody());
		}
	}

}
