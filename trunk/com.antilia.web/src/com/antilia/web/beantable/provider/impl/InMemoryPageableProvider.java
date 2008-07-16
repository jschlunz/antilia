/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.provider.ILoadablePageableProvider;
import com.antilia.web.beantable.provider.IPageableProviderNavigationListener;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class InMemoryPageableProvider<E extends Serializable> implements ILoadablePageableProvider<E> {

	public static final int DEFAULT_PAGE_SIZE = 10;
	 
	private static final long serialVersionUID = 1L;	
	
	private ArrayList<E> collection;
	
	private int currentIndex;
	
	private int currentPage;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
		
	private List<IPageableProviderNavigationListener>  navigationListeners = new ArrayList<IPageableProviderNavigationListener>();

	/**
	 * Constructor.
	 * 
	 * @param collection
	 */
	public InMemoryPageableProvider(Collection<E> collection) {
		this.collection = new ArrayList<E>(collection);		
		this.currentIndex = 0;
		this.currentPage = 0;
	}
	
	public InMemoryPageableProvider(Iterable<E> iterable) {
		this.collection = toList(iterable);		
		this.currentIndex = 0;
		this.currentPage = 0;
	}
	
	@Override
	public final  void load(Query<E> filter) {
		Collection<E> collection = onLoad(filter);
		if(collection != null) {
			this.collection = new ArrayList<E>(collection);				
		}
		for(IPageableProviderNavigationListener listener: navigationListeners) {
			listener.onClear();
		}
		this.currentIndex = 0;
		this.currentPage = 0;
	}	
	
	@Override
	public void reset() {
		this.currentIndex = 0;
		this.currentPage = 0;
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
	
	
	@Override
	public boolean update(E bean) {
		return false;
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
	public Iterator<E> getCurrentPage() {
		return collection.subList((currentPage*pageSize), Math.min(((currentPage+1)*pageSize),collection.size())).listIterator();
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
	public Iterator<E> nextPage() {		
		if(hasNextPage()) {
			currentPage++;	
			for(IPageableProviderNavigationListener listener: navigationListeners) {
				listener.onNextPage();
			}
		}
		return getCurrentPage();
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#previousPage()
	 */
	public Iterator<E> previousPage() {
		if(hasPreviousPage()) {
			currentPage--;
			for(IPageableProviderNavigationListener listener: navigationListeners) {
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

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#iterator()
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
	
	public Iterator<E> firstPage() {
		if(!isEmpty()) {
			currentPage = 0;
			for(IPageableProviderNavigationListener listener: navigationListeners) {
				listener.onFirstPage();
			}
			return getCurrentPage();
		}
		return null;
	}

	public Iterator<E> lastPage() {
		if(!isEmpty()) {
			currentPage = getNumberOfPages()-1;
			for(IPageableProviderNavigationListener listener: navigationListeners) {
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

	public void addNavigationListener(IPageableProviderNavigationListener listener) {
		navigationListeners.add(listener);
	}

	public Iterator<IPageableProviderNavigationListener> getNavigationListeners() {
		return navigationListeners.iterator();
	}

	public void removeNavigationListener(
			IPageableProviderNavigationListener listener) {
		navigationListeners.remove(listener);
	}
	
}
