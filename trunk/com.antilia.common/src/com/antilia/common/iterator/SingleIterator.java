package com.antilia.common.iterator;

import java.util.Iterator;

/**
 * <p>
 * <code>SingleIterator</code> is an {@link Iterator} over a single object
 * instance.
 * </p>
 */
public class SingleIterator<E> implements Iterator<E> {
	private E object;

	public SingleIterator(E object) {
		this.object = object;
	}

	public boolean hasNext() {
		return(object != null);
	}

	public E next() {
		E tmp = object;
		object = null;
		return(tmp);
	}

	public void remove() {
		throw new UnsupportedOperationException("remove() is not supported by this iterator");
	}
}