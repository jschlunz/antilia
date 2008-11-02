/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class RefreshButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	public RefreshButton() {
		super("refresh", true);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_REFRESH;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		IPageableComponent<E> component = findPageableComponent();
		component.getPageableProvider().reset();
		target.addComponent((Component)component);
	}

	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
