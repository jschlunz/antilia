package com.antilia.common.iterator;

import java.io.Serializable;
import java.util.Iterator;

public class ArrayIterable<E> implements Iterable<E>, Serializable {
	private static final long serialVersionUID = 1L;

	private E[] array;
	
	public ArrayIterable(E[] array) {
		this.array = array;
	}

	public Iterator<E> iterator() {
		return(new ArrayIterator<E>(array));
	}
}
