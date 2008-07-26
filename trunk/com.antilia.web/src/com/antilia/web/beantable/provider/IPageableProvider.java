/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;
import java.util.Iterator;

import com.antilia.hibernate.query.IQuery;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IPageableProvider<E extends Serializable> extends IProvider<E> {

	int getPageSize();
	
	void setPageSize(int size);
	
	Iterator<E> getCurrentPage();
	
	Iterator<E> firstPage();
	
	Iterator<E> nextPage();
	
	Iterator<E> previousPage();
	
	Iterator<E> lastPage();	
	
	int getNumberOfPages();	
	
	int currentPageNumber();
	
	void reset();
	
	boolean update(E bean);
	
	boolean hasNextPage();
	
	boolean hasPreviousPage();
	
	void addNavigationListener(IPageableProviderNavigationListener listener);
	
	void removeNavigationListener(IPageableProviderNavigationListener listener);
	
	Iterator<IPageableProviderNavigationListener> getNavigationListeners();
	
	IQuery<E> getQuery();
}
