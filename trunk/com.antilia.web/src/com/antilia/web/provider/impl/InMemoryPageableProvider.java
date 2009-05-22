/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.provider.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.navigator.ILoadablePageableNavigator;
import com.antilia.web.navigator.IPageableNavigator;
import com.antilia.web.navigator.IPageableNavigatorListener;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class InMemoryPageableProvider<E extends Serializable> implements ILoadablePageableNavigator<E> {

	public static final int DEFAULT_PAGE_SIZE = 10;
	 
	private static final long serialVersionUID = 1L;	
	
	private ArrayList<E> collection;
	
	private int currentIndex;
	
	private int currentPage;
	
	private IQuery<E> query;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
		
	private List<IPageableNavigatorListener>  navigationListeners = new ArrayList<IPageableNavigatorListener>();


	
	/**
	 * Constructor.
	 * 
	 * @param collection
	 */
	public InMemoryPageableProvider(Collection<E> collection, Class<E> beanClass) {
		this.collection = toList(collection);		
		this.currentIndex = 0;
		this.currentPage = 0;
		this.query  = new Query<E>(beanClass);
	}
	
	public InMemoryPageableProvider(Iterable<E> iterable, Class<E> beanClass) {
		this.collection = toList(iterable);		
		this.currentIndex = 0;
		this.currentPage = 0;
		this.query  = new Query<E>(beanClass);
	}
	
	
	public final  void load(Query<E> filter) {
		Collection<E> collection = onLoad(filter);
		if(collection != null) {
			this.collection = toList(collection);
		}
		for(IPageableNavigatorListener listener: navigationListeners) {
			listener.onClear();
		}
		this.currentIndex = 0;
		this.currentPage = 0;
	}	
	
	public void reset() {
		this.currentIndex = 0;
		this.currentPage = 0;
	}	
	
	public void clearcache() {
		// do nothing
	}
	
	/**
	 * Override  this method to handle custom collection loading 
	 * @return
	 */
	protected Collection<E> onLoad(Query<E> filter) {
		return null;
	}
	
	
	private ArrayList<E> toList(Iterable<E> iterable) {
		ArrayList<E> list = new ArrayList<E>();
		if(iterable != null) {
			for(E e: iterable) {
				list.add(e);
			}
		}
		return list;
	}
	
	
	public boolean removeCurrent() {
		if(collection.size()>0 && currentIndex>=0 && currentIndex < collection.size()) {
			collection.remove(currentIndex);
			if(collection.size() == 0) 
				currentIndex = 0;
			else if (currentIndex > (collection.size()-1)){
				currentIndex = collection.size() - 1;
			} 
			return true;
		}		
		return false;
	}
	
	public void update(E bean) {
		// is not going to work?
		int i = collection.indexOf(bean);
		if(i >= 0) {
			collection.remove(i);
			collection.set(i, bean);
		}	
	}
	
	public void updateAll(Collection<E> element) {
		
	}

	public void add(E element) {
		collection.add(element);
	}
	
	public void addAll(Collection<E> element) {
		collection.addAll(toList(element));
	}
	
	public void remove(E element) {		
		collection.remove(element);
	}
	
	public void removeAll(Collection<E> element) {
		collection.removeAll(element);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#currentPageNumber()
	 */
	public int currentPageNumber() {
		return currentPage;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sonullurces.IPageableSource#getCurrentPage()
	 */
	public Iterator<IModel<E>> getCurrentPage() {
		return toModelList(collection.subList((currentPage*pageSize), Math.min(((currentPage+1)*pageSize),collection.size()))).iterator();
	}
	
	private List<IModel<E>> toModelList(Iterable<E> list) {
		List<IModel<E>> wrappedList = new ArrayList<IModel<E>>();
		for(E bean: list) {
			wrappedList.add(new CompoundPropertyModel<E>(bean));
		}
		return wrappedList;
	}
	

	public boolean hasNext() {
		return currentIndex < (size()-1);
	}
	
	public boolean hasPrevious() {
		return currentIndex > 0;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#getNumberOfPages()
	 */
	public int getNumberOfPages() {
		if(collection != null) {
			int size = collection.size();
			return (size/pageSize)+(((size%pageSize)==0)?0:1);
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#getPageSize()
	 */
	public int getPageSize() {
		return pageSize;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#hasNextPage()
	 */
	public boolean hasNextPage() {
		return (currentPage<getNumberOfPages()-1);
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#hasPreviousPage()
	 */
	public boolean hasPreviousPage() {
		return currentPage>0;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#nextPage()
	 */
	public Iterator<IModel<E>> nextPage() {		
		if(hasNextPage()) {
			currentPage++;	
			for(IPageableNavigatorListener listener: navigationListeners) {
				listener.onNextPage();
			}
		}
		return getCurrentPage();
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#previousPage()
	 */
	public Iterator<IModel<E>> previousPage() {
		if(hasPreviousPage()) {
			currentPage--;
			for(IPageableNavigatorListener listener: navigationListeners) {
				listener.onPreviousPage();
			}
		}
		return getCurrentPage();
	}

	
	public void setPageSize(int size) {
		this.pageSize = size;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#current()
	 */
	public E current() {
		return collection.get(currentIndex);
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#currentIndex()
	 */
	public int currentIndex() {
		return currentIndex;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#first()
	 */
	public E first() {
		if(!isEmpty()) {
			currentIndex = 0;
			return current();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#get(int)
	 */
	public E get(int index) {
		if(0<=index  && index < size())
			collection.get(index);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#isEmpty()
	 */
	public boolean isEmpty() {
		if(collection != null)
			return collection.isEmpty();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IProvider#iterator()
	 */
	public Iterator<E> iterator() {
		return collection.iterator();
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#last()
	 */
	public E last() {
		if(!isEmpty()) {
			currentIndex = size()-1;
			return current();
		}
		return null;
	}
	
	public Iterator<IModel<E>> firstPage() {
		if(!isEmpty()) {
			currentPage = 0;
			for(IPageableNavigatorListener listener: navigationListeners) {
				listener.onFirstPage();
			}
			return getCurrentPage();
		}
		return null;
	}

	public Iterator<IModel<E>> lastPage() {
		if(!isEmpty()) {
			currentPage = getNumberOfPages()-1;
			for(IPageableNavigatorListener listener: navigationListeners) {
				listener.onLastPage();
			}
			return getCurrentPage();
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#next()
	 */
	public E next() {
		if(currentIndex < (size()-1)) {
			currentIndex++;
			return current();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#previous()
	 */
	public E previous() {
		if(currentIndex>0) {
			currentIndex--;
			return current();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#size()
	 */
	public int size() {
		if(collection != null)
			return collection.size();
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IDetachable#detach()
	 */
	public void detach() {
		
	}

	public void addNavigationListener(IPageableNavigatorListener listener) {
		navigationListeners.add(listener);
	}

	public Iterator<IPageableNavigatorListener> getNavigationListeners() {
		return navigationListeners.iterator();
	}

	public void removeNavigationListener(
			IPageableNavigatorListener listener) {
		navigationListeners.remove(listener);
	}

	/**
	 * @return the query
	 */
	public IQuery<E> getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(IQuery<E> query) {
		this.query = query;
	}

	/**
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public IPageableNavigator<E> duplicate() {
		ArrayList<E> newColection = new ArrayList<E>(this.collection);
		return new InMemoryPageableProvider<E>(newColection, this.query.getEntityClass());
	}
}
