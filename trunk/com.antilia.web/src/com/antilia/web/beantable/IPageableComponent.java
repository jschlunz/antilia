/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.IFeedback;

import com.antilia.web.beantable.provider.IPageableProvider;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IPageableComponent<E extends Serializable> {

	/**
	 * Returns the provider of the component.
	 * @return
	 */
	IPageableProvider<E> getPageableProvider();
	
	/**
	 * Returns the component to be update when navigating.
	 * @return
	 */
	Component getUpdatableComponent();
	
	/**
	 * Returns the element to provide feeback when table operations fail.
	 * @return
	 */
	IFeedback getFeedback();
	
}
