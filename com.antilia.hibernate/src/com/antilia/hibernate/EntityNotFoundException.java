package com.antilia.hibernate;

import java.io.Serializable;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class EntityNotFoundException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Class<?> clazz, Serializable key) {
		super(PersistenceException.EMPTY_NOT_FOUND);
	}

}
