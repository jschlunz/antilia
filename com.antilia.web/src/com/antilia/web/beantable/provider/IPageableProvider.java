/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.model.IModel;

import com.antilia.hibernate.query.IQuery;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IPageableProvider<E extends Serializable> extends IProvider<E>, IUpdatable<E> {

	int getPageSize();
	
	void setPageSize(int size);
	
	Iterator<IModel<E>> getCurrentPage();
	
	Iterator<IModel<E>> firstPage();
	
	Iterator<IModel<E>> nextPage();
	
	Iterator<IModel<E>> previousPage();
	
	Iterator<IModel<E>> lastPage();	
	
	int getNumberOfPages();	
	
	int currentPageNumber();
	
	void reset();
	
	void clearcache();
	
	boolean hasNextPage();
	
	boolean hasPreviousPage();
	
	void addNavigationListener(IPageableProviderNavigationListener listener);
	
	void removeNavigationListener(IPageableProviderNavigationListener listener);
	
	Iterator<IPageableProviderNavigationListener> getNavigationListeners();
	
	IQuery<E> getQuery();
	
	public IPageableProvider<E> duplicate();
}
