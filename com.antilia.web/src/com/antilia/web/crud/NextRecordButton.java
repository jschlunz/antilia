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
public class NextRecordButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	public NextRecordButton() {
		super("next", true);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_NEXT_ENABLED;
	}
	
	@Override
	protected ResourceReference getDisabledImage() {
		return DefaultStyle.IMG_NEXT_DISABLED;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "Next";
	}
	
	@Override
	public boolean isVisible() {
		EditPanel<E> component = findEditPanel();
		return component.getPageableProvider().size()>1;
	}
	
	@Override
	public boolean isEnabled() {
		EditPanel<E> component = findEditPanel();
		return component.getPageableProvider().hasNext();
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		EditPanel<E> component = findEditPanel();
		component.getPageableProvider().next();
		target.addComponent((Component)component);
	}
	
	
	@Override
	public void onSubmit() {
		
	}

	@SuppressWarnings("unchecked")
	private EditPanel<E> findEditPanel() {
		return (EditPanel<E>)findParent(EditPanel.class);
	}
}
