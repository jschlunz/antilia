/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
	
	private Set<E> selected;
	
	public SourceSelector(IPageableProvider<E> pageableSource) {
		this(pageableSource, SelectionMode.MULTIPLE);
	}
	
	public SourceSelector(IPageableProvider<E> pageableSource, SelectionMode selectionMode) {
		this.pageableSource = pageableSource;
		this.pageableSource.addNavigationListener(this);
		this.selectionMode = selectionMode;
		this.selected = new TreeSet<E>();
	}

	public void addToSelected(E bean) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return;
		if(getSelectionMode().equals(SelectionMode.SINGLE))
			selected.clear();
		selected.add(bean);
	}

	public void addToSelected(int indexInCurrentPage) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return;
		Iterator<E> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next();
			if(indexInCurrentPage == count) 
				addToSelected(bean);
			count++;
		}
	}
	
	public boolean clear() {
		onClear();
		return true;
	}

	public Set<E> getSelected() {
		return selected;
	}
	

	public boolean isSelected(E bean) {
		return selected.contains(bean);
	}

	public boolean isSelected(int indexInCurrentPage) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return false;
		Iterator<E> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next();
			if(indexInCurrentPage == count) 
				return isSelected(bean);
			count++;
		}
		return false;
	}

	public void removeFromSelected(E bean) {
		selected.remove(bean);
	}

	public void removeFromSelected(int indexInCurrentPage) {
		Iterator<E> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next();
			if(indexInCurrentPage == count) 
				removeFromSelected(bean);
			count++;
		}
	}

	public void toggleSelected(int indexInCurrentPage) {
		if(getSelectionMode().equals(SelectionMode.NONE))
			return;
		if(isSelected(indexInCurrentPage)) {
			removeFromSelected(indexInCurrentPage);
		} else {
			addToSelected(indexInCurrentPage);
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
		Iterator<E> pageIterator = getPageableSource().getCurrentPage();
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next();
			if(this.pageSelected) 
				addToSelected(bean);
			else 
				removeFromSelected(bean);
		}
	}
	
	public void revertPageSelection() {
		Iterator<E> pageIterator = getPageableSource().getCurrentPage();
		int count = 0;
		while(pageIterator.hasNext()) {
			E bean = pageIterator.next();
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
	

	@Override
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
