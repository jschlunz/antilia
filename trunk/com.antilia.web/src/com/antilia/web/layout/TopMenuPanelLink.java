/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class TopMenuPanelLink<B extends Serializable> extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private String label;
	
	private IContainer page;


	/**
	 * 
	 * @param label
	 * @param page
	 * @param crudClass
	 * @param beanClass
	 */
	public TopMenuPanelLink(String label, IContainer page) {
		super(label);
		this.label = label;
		this.page = page;
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
	
	@Override
	protected String getLabelKey() {
		return this.label;
	}
	

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void onClick(AjaxRequestTarget target) {
		if(target != null) {
			TopMenuPanel<B> modalContainer = createTopMenuPanel(IContainer.BODY_CONTENT_ID);
			this.page.getBody().addOrReplace(modalContainer);
			target.addComponent(this.page.getBody());
		}
	}

	
	protected abstract TopMenuPanel<B> createTopMenuPanel(String id);
	
}
