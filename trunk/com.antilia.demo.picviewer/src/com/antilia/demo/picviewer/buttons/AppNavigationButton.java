/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.buttons;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.demo.picviewer.Index;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract  class AppNavigationButton extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private Index page;
	
	public AppNavigationButton(String id, Index page) {
		super(id, true);	
		this.page = page;
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		this.page.setBody(getBodyPanel("body"));
		target.addComponent(this.page.getContents());
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
