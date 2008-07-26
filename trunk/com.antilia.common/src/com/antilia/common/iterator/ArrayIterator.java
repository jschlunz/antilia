package com.antilia.common.iterator;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {
	private E[] array;
	private int index = 0;
	
	public ArrayIterator(E[] array) {
		this.array = array;
	}
	
	public boolean hasNext() {
		return(index < array.length);
	}

	public E next() {
		return(array[index++]);
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}