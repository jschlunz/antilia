/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.Form;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class BeanForm<B extends Serializable> extends Form<B> {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	
	/**
	 * @param id
	 */
	public BeanForm(String id, BeanProxy<B> beanProxy) {
		super(id);
		this.beanProxy = beanProxy;
	}
	
	public BeanProxy<B> getBeanProxy() {
		return beanProxy;
	}
	

}
