/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import com.antilia.common.query.Query;
import com.antilia.web.field.BeanProxy;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ILoadablePanel<B extends Serializable> extends IFeedBackAware {

	public Query<B> getFilterQuery();
	
	public BeanProxy<B> getBeanProxy();
	
	/**
	 * Reload the search panel.
	 */
	public void reload();

}
