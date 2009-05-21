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

import org.apache.wicket.model.IModel;

import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.IPageableProviderNavigationListener;
import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.beantable.provider.SelectionMode;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SourceSelector<E extends Serializable> implements IProviderSelector<E>, IPageableProviderNavigationListener {

	private static final long serialVersionUID = 1L;

	private IPageableProvider<E> pageableSource;
	
	private boolean pageSelected = false;
	
	private SelectionMode selectionMode;
	
	//private Set<E> selected;
	
	private List<E> selected;
	
	public SourceSelector(IPageableProvider<E> pageableSource) {
		this(pageableSource, SelectionMode.MULTIPLE);
	}
	
	public SourceSelector(IPageableProvider<E> pageableSource, SelectionMode selectionMode) {
		this.pageableSource = pageableSource;
		this.pageableSource.addNavigationListener(this);
		this.selectionMode = selectionMode;
		//this.selected = new TreeSet<E>();
		//this.selected = new HashSet<E>();
		this.selected = new ArrayList<E>();
	}

	public void addToSelected(E bean) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return;
		if(getSelectionMode().equals(SelectionMode.SINGLE))
			selected.clear();
		selected.add(bean);
	}

	public E addToSelected(int indexInCurrentPage) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return null;
		Iterator<IModel<E>> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next().getObject();
			if(indexInCurrentPage == count) {
				addToSelected(bean);
				return bean;
			}
			count++;
		}
		return null;
	}
	
	public boolean clear() {
		onClear();
		return true;
	}

	public Collection<E> getSelected() {
		return selected;
	}
	

	public boolean isSelected(E bean) {
		return selected.contains(bean);
	}

	public boolean isSelected(int indexInCurrentPage) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return false;
		Iterator<IModel<E>> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next().getObject();
			if(indexInCurrentPage == count) 
				return isSelected(bean);
			count++;
		}
		return false;
	}

	public void removeFromSelected(E bean) {
		selected.remove(bean);
	}

	public E removeFromSelected(int indexInCurrentPage) {
		Iterator<IModel<E>> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next().getObject();
			if(indexInCurrentPage == count) {
				removeFromSelected(bean);
				return bean;
			}
			count++;
		}
		return null;
	}

	public E toggleSelected(int indexInCurrentPage) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return null;
		if(isSelected(indexInCurrentPage)) {
			return removeFromSelected(indexInCurrentPage);
		} else {
			return addToSelected(indexInCurrentPage);
		}
	}

	public SelectionMode getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(SelectionMode selectionMode) {
		this.selectionMode = selectionMode;
	}
	
	public void togglePageSelection() {
		this.pageSelected = !this.pageSelected;
		Iterator<IModel<E>> pageIterator = getPageableSource().getCurrentPage();
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next().getObject();
			if(this.pageSelected) 
				addToSelected(bean);
			else 
				removeFromSelected(bean);
		}
	}
	
	public void revertPageSelection() {
		Iterator<IModel<E>> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next().getObject();
			toggleSelected(bean);
			count++;
		}
	}
	
	public void toggleSelected(E bean) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return;
		if(isSelected(bean)) {
			removeFromSelected(bean);
		} else {
			addToSelected(bean);
		}
	}

	public boolean isPageSelected() {
		return pageSelected;
	};
	
	
	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSourceNavigationListener#onFirstPage()
	 */
	public void onFirstPage() {
		this.pageSelected = false;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSourceNavigationListener#onLastPage()
	 */
	public void onLastPage() {	
		this.pageSelected = false;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSourceNavigationListener#onNextPage()
	 */
	public void onNextPage() {
		this.pageSelected = false;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.IPageableSourceNavigationListener#onPreviousPage()
	 */
	public void onPreviousPage() {
		this.pageSelected = false;
	}
	

	public void onClear() {
		pageSelected = false;
		selected.clear();
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISourceNavigationListener#onFirst()
	 */
	public void onFirst() {

	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISourceNavigationListener#onLast()
	 */
	public void onLast() {

	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISourceNavigationListener#onNext()
	 */
	public void onNext() {

	}

	/* (non-Javadoc)
	 * @see com.antilia.common.sources.ISourceNavigationListener#onPrevious()
	 */
	public void onPrevious() {

	}

	public IPageableProvider<E> getPageableSource() {
		return pageableSource;
	}

	public void setPageableSource(IPageableProvider<E> pageableSource) {
		this.pageableSource = pageableSource;
	}

}
