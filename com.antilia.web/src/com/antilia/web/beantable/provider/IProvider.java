/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.model.IDetachable;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IProvider<E extends Serializable> extends IDetachable {
	
	E first();
	
	E next();
	
	E current();
	
	boolean hasNext();
	
	boolean hasPrevious();
	
	int currentIndex();
	
	Iterator<E> iterator();
	
	E previous();
	
	E last();
	
	int size();
	
	boolean isEmpty();
	
	E get(int index);
	
}
