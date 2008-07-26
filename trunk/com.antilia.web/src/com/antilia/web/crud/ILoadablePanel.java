/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import com.antilia.hibernate.query.Query;
import com.antilia.web.field.BeanProxy;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ILoadablePanel<B extends Serializable> {

	public Query<B> getFilterQuery();
	
	public BeanProxy<B> getBeanProxy();
	
	public void reload();
}
