/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;
import java.util.Iterator;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IProviderSelector<E extends Serializable> extends  Serializable {

	void addToSelected(E bean);
	
	void addToSelected(int indexInCurrentPage);
	
	void toggleSelected(int indexInCurrentPage);
	
	void toggleSelected(E bean);
	
	void removeFromSelected(E bean);
	
	void removeFromSelected(int indexInCurrentPage);
	
	void togglePageSelection();
		
	void revertPageSelection();
	
	boolean isSelected(E bean);
	
	boolean isSelected(int indexInCurrentPage);	
	
	boolean isPageSelected();
	
	Iterator<E> getSelected();
	
	void setSelectionMode(SelectionMode selectionMode);
	
	SelectionMode getSelectionMode();
}
