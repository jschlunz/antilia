/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.Component;

/**
 *  A CRUD panel containing a title.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TitledCRUDPanel<B extends Serializable> extends CRUDPanel<B> {
	

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 * @param beanClass
	 */
	public TitledCRUDPanel(String id, Class<B> beanClass) {
		super(id, beanClass);
	}

	/**
	 * @param id
	 * @param styler
	 */
	public TitledCRUDPanel(String id, CrudStyler<B> styler) {
		super(id, styler);
	}
	
	@Override
	protected Component newBeforeBody(String id, CrudStyler<B> styler) {
		return new CRUDTitle(id, new CRUDTitle.CRUDInfo(styler.getBeanClass(), getCrudMode()));
	}

}
