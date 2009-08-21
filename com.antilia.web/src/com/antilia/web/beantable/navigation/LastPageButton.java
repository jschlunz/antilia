/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.beantable.IPageableNavigationListener;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.utils.RequestUtils;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class LastPageButton<E extends Serializable> extends PageableButton<E> {

	private static final long serialVersionUID = 1L;

	public LastPageButton() {
		super("last");
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		if(RequestUtils.isBrowserIeExplorer6())
			return DefaultStyle.IMG_LAST_ENABLED;
		return DefaultStyle.IMG_LAST_ENABLED_PNG;
	}
	
	@Override
	protected ResourceReference getDisabledImage() {
		if(RequestUtils.isBrowserIeExplorer6())
			return DefaultStyle.IMG_LAST_DISABLED;
		return DefaultStyle.IMG_LAST_DISABLED_PNG;
	}

	@Override
	protected String getLabelKey() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "";
	}
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		IPageableComponent<E> component = findPageableComponent();
		component.getPageableNavigator().lastPage();
		if(!component.isKeepSelectionOnNavigation() && component.getSourceSelector() != null) {
			component.getSourceSelector().clear();
		}
		target.addComponent(component.getUpdatableComponent());
		if(component instanceof IPageableNavigationListener) {
			IPageableNavigationListener listener = (IPageableNavigationListener)component;
			listener.onLastPage(target);
		}
	}
	
	@Override
	public boolean isEnabled() {
		IPageableComponent<E> component = findPageableComponent();
		return component.getPageableNavigator().hasNextPage();
	}

}
