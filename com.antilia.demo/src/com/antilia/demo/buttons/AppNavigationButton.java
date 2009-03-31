/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.buttons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.demo.Index;
import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract  class AppNavigationButton extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private Index page;
	
	public AppNavigationButton(String id, Index page) {
		super(id);	
		this.page = page;
	}
	
	
	@Override
	protected void onClick(AjaxRequestTarget target) {
		this.page.setContent(getBodyPanel("content"));
		target.addComponent(this.page.getBody());
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_TRANSPARENT;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "tables";
	}
	
	public abstract WebMarkupContainer getBodyPanel(String id);

}
