/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IUpdatable<E extends Serializable> {

	void add(E element);
	
	void addAll(Collection<E> element);	
	
	void update(E element);
	
	void updateAll(Collection<E> element);
	
	void remove(E element);
	
	void removeAll(Collection<E> element);
}
