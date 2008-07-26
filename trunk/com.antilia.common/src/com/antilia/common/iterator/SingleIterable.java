package com.antilia.common.iterator;

import java.io.Serializable;
import java.util.Iterator;

public class SingleIterable<E> implements Iterable<E>, Serializable {
	private static final long serialVersionUID = 1L;

	private E object;
	
	public SingleIterable(E object) {
		this.object = object;
	}

	public Iterator<E> iterator() {
		return(new SingleIterator<E>(object));
	}
}
