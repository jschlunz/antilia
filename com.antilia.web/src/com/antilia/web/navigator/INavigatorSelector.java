/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.navigator;

import java.io.Serializable;
import java.util.Collection;

import com.antilia.web.provider.SelectionMode;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface INavigatorSelector<E extends Serializable> extends  Serializable {

	void addToSelected(E bean);
	
	E addToSelected(int indexInCurrentPage);
	
	E toggleSelected(int indexInCurrentPage);
	
	void toggleSelected(E bean);
	
	void removeFromSelected(E bean);
	
	E removeFromSelected(int indexInCurrentPage);
	
	void togglePageSelection();
		
	void revertPageSelection();
	
	boolean isSelected(E bean);
	
	boolean isSelected(int indexInCurrentPage);	
	
	boolean isPageSelected();
	
	boolean clear();
	
	Collection<E> getSelected();
		
	void setSelectionMode(SelectionMode selectionMode);
	
	SelectionMode getSelectionMode();
}
