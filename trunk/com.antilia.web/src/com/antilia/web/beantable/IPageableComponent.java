/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.beantable.provider.IPageableProvider;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IPageableComponent<E extends Serializable> {

	IPageableProvider<E> getPageableProvider();
	
	Component getUpdatableComponent();
	
}
