/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ReloadButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	public ReloadButton() {
		super("load", true);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_LOAD;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Load";
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form form) {
			ILoadablePanel<E> loadablePanel = getLoadablePanel();
			if(loadablePanel != null) {				
				loadablePanel.reload();
				target.addComponent((Component)loadablePanel);
			}		
	}

	@SuppressWarnings("unchecked")
	public ILoadablePanel<E> getLoadablePanel() {
		return (ILoadablePanel<E>)findParent(ILoadablePanel.class);
	}
}
