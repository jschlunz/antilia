package com.antilia.common.iterator;

import java.io.Serializable;
import java.util.Iterator;

public class EmptyIterable<E> implements Iterable<E>, Serializable {
	private static final long serialVersionUID = 1L;

	public Iterator<E> iterator() {
		return(new EmptyIterator<E>());
	}
}