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

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.provider.ILoadablePageableProvider;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.IPageableProviderNavigationListener;
import com.antilia.web.beantable.provider.IQuerable;
import com.antilia.web.beantable.provider.IUpdatable;


/**
 * Wrapper pageable provider around an IDataProvider<E>.
 * 
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DataProviderPageableProvider<E extends Serializable> implements ILoadablePageableProvider<E> {

	public static final int DEFAULT_PAGE_SIZE = 10;
	
	public static final int UNDEFINED_SIZE= -1;
	
	private static final long serialVersionUID = 1L;	
	
	 private IQuery<E> query;
	
	// the current index...	starts in 0 
	private int currentIndex;
	
	// page start currentPage*pageSize
	private int pageStart;
	
	// page end Min(pageStart+pageSize-1, total-1)
	private int pageEnd;
	
	private int currentPage;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private int totalSize = UNDEFINED_SIZE;
		
	private List<IPageableProviderNavigationListener>  navigationListeners = new ArrayList<IPageableProviderNavigationListener>();
	
	private List<IModel<E>> cachedEntities;
	
	private IDataProvider<E> dataProvider;

	public DataProviderPageableProvider(IDataProvider<E> dataProvider, IQuery<E> query, boolean shouldLoad) {
		this.query = query;
		this.dataProvider = dataProvider;
		this.currentIndex = 0;
		this.currentPage = 0;
		this.totalSize = UNDEFINED_SIZE;
		if(shouldLoad)
			getCachedEntities();
	}	
	
	/**
	 * Constructor.
	 * 
	 * @param collection
	 */
	public DataProviderPageableProvider(IDataProvider<E> dataProvider, IQuery<E> query) {
		this(dataProvider, query, false);
	}
	
	public void load(Query<E> filter) {
		this.currentIndex = 0;
		this.currentPage = 0;
		this.totalSize = UNDEFINED_SIZE;
		for(IPageableProviderNavigationListener listener: navigationListeners) {
			listener.onClear();
		}
		clearCached();
		getCachedEntities();
		currentPage = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSource#currentPageNumber()
	 */
	public int currentPageNumber() {
		return currentPage;
	}

	public void reset() {
		this.currentIndex = 0;
		this.currentPage = 0;
		this.totalSize = UNDEFINED_SIZE;
		for(IPageableProviderNavigationListener listener: navigationListeners) {
			listener.onClear();
		}
		clearCached();
	}
	
	public void clearcache() {
		clearCached();
	}
	
	@SuppressWarnings("unchecked")
	public void add(E element) {
		if(dataProvider instanceof IUpdatable) {
			((IUpdatable<E>)dataProvider).add(element);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void addAll(Collection<E> element) {
		if(dataProvider instanceof IUpdatable) {
			((IUpdatable<E>)dataProvider).addAll(element);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void remove(E element) {
		if(dataProvider instanceof IUpdatable) {
			((IUpdatable<E>)dataProvider).remove(element);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void removeAll(Collection<E> element) {
		if(dataProvider instanceof IUpdatable) {
			((IUpdatable<E>)dataProvider).removeAll(element);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void updateAll(Collection<E> element) {
		if(dataProvider instanceof IUpdatable) {
			((IUpdatable<E>)dataProvider).updateAll(element);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void update(E bean) {
		if(dataProvider instanceof IUpdatable) {
			((IUpdatable<E>)dataProvider).update(bean);
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IPageableProvider#getCurrentPage()
	 */
	public Iterator<IModel<E>> getCurrentPage() {		
		return getCachedEntities().iterator();
	}

	/**
	 * @return the cachedEntities
	 */
	@SuppressWarnings("unchecked")
	private List<IModel<E>> getCachedEntities() {
		if(cachedEntities == null) {
			int start = (currentPage*pageSize);
			currentIndex = pageStart = start;			
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			if(dataProvider instanceof IQuerable) {
				((IQuerable<E>)dataProvider).setQuery(query);			
			}
			cachedEntities = new ArrayList<IModel<E>>();
			Iterator<E> it = (Iterator<E>)dataProvider.iterator(start, pageSize);
			while(it.hasNext()) {
				cachedEntities.add(dataProvider.model(it.next()));
			}			
			totalSize = getTotalSize();
			pageEnd = Math.min((pageStart+pageSize)-1,totalSize-1);
		}
		return cachedEntities;
	}
	
	
	private List<E> getRealObject(Iterable<IModel<E>> iterable) {
		List<E> list = new ArrayList<E>();
		for(IModel<E> model: iterable) {
			list.add(model.getObject());
		}
		return list;
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
		if(getQuery() != null) {
			int size = getTotalSize();
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
			clearCached();
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
	public Iterator<IModel<E>> previousPage() {
		if(hasPreviousPage()) {
			clearCached();
			currentPage--;			
			for(IPageableProviderNavigationListener listener: navigationListeners) {
				listener.onPreviousPage();
			}
		}
		return getCurrentPage();
	}
	
	private void clearCached() {
		this.cachedEntities = null;
	}

	
	public void setPageSize(int size) {
		this.pageSize = size;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#current()
	 */
	public E current() {
		return getCachedEntities().get(currentIndex-pageStart).getObject();
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
			getCachedEntities().get(index);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#isEmpty()
	 */
	public boolean isEmpty() {
		if(getCachedEntities() != null)
			return getCachedEntities().isEmpty();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.antilia.web.beantable.provider.IProvider#iterator()
	 */
	public Iterator<E> iterator() {
		return getRealObject(getCachedEntities()).iterator();
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
			clearCached();
			currentPage = 0;
			for(IPageableProviderNavigationListener listener: navigationListeners) {
				listener.onFirstPage();
			}
			return getCurrentPage();
		}
		return null;
	}

	public Iterator<IModel<E>> lastPage() {
		if(!isEmpty()) {
			clearCached();
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
		if(currentIndex <= (size()-1)) {			
			currentIndex++;
			if(currentIndex > pageEnd) {				
				currentIndex--;
				if(hasNext()) {
					nextPage();
				}
			}
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
			if(currentIndex < pageStart)
				clearCached();
			return current();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISource#size()
	 */
	public int size() {
		getCachedEntities();
		return totalSize;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IDetachable#detach()
	 */
	public void detach() {
		if(dataProvider != null) {
			dataProvider.detach();
		}
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
	 * @return the totalSize
	 */
	public int getTotalSize() {
		if(totalSize == UNDEFINED_SIZE) {
			totalSize = dataProvider.size();
		}
		return totalSize;
	}


	public IPageableProvider<E> duplicate() {
		return new DataProviderPageableProvider<E>(this.dataProvider, this.query, true);
	}
	
}
