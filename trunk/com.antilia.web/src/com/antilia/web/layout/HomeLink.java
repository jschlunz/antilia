/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

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
public class HomeLink extends AbstractLink {

	private static final long serialVersionUID = 1L;

	public HomeLink(String id) {
		super(id);
	}
	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_BACK;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Back To Home";
	}
	
	@Override
	protected String getLabelKey() {
		return "BackToHome";		
	}
	
	@Override
	protected String getTitleKey() {
		return "BackToHome.title";
	}	

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void onClick(AjaxRequestTarget target) {
		IContainer index = findHome();
		if(target != null) {
			index.getBody().addOrReplace(index.getContent());
			target.addComponent(index.getBody());
		}		
	}
	
	private IContainer findHome() {
		return (IContainer)findParent(IContainer.class);
	}
}
