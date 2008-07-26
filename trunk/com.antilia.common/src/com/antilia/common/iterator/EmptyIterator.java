package com.antilia.common.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIterator<E> implements Iterator<E> {

	public Iterator<E> iterator() {
		return(this);
	}

	public boolean hasNext() {
		return false;
	}

	public E next() {
		throw new NoSuchElementException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}