/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.web.layout;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.common.util.ClassUtils;
import com.antilia.web.crud.CRUDPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ScopedCrudPanel<T extends Serializable> extends ScopedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Class<T> beanClass;
	
	private Class<CRUDPanel<T>> crudClass;

	
	/**
	 * @param id
	 */
	public ScopedCrudPanel(String id, Class<T> beanClass, Class<CRUDPanel<T>> crudClass) {
		super(id);
		this.beanClass = beanClass;
		this.crudClass = crudClass;
	}
	
	/**
	 * @param id
	 */
	public ScopedCrudPanel(String id, Class<T> beanClass) {
		super(id);
		this.beanClass = beanClass;
	}
	

	@Override
	protected final Component createBody(String id) {
		return createCrudPanel(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	protected CRUDPanel<T> createCrudPanel(String id) {
		try {
			if(this.crudClass != null)
				return (CRUDPanel<T>)ClassUtils.createInstance(this.crudClass, id);
		} catch (Exception e) {
			// fall-back to default						
		}
		return new BackToHomeCRUD<T>(id, beanClass);
	}
}
