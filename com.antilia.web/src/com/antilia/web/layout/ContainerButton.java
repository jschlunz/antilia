/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import org.apache.wicket.Component;
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
public abstract class ContainerButton extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private String label;
	
	private IContainer page;
	
	private String contentId;
	
	public ContainerButton(String label, IContainer page,  String contentId) {
		super(label);
		this.label = label;
		this.page = page;
		this.contentId = contentId;
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
			ScopedPanel modalContainer = new ScopedPanel(contentId) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected Component createBody(String id) {
					return ContainerButton.this.createBody(id);
				}
			}; 
			this.page.getBody().addOrReplace(modalContainer);
			target.addComponent(this.page.getBody());
		}
	}
	
	protected abstract Component createBody(String id);

}
