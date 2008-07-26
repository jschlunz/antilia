/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;

import com.antilia.hibernate.query.Query;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ILoadable<E extends Serializable> {
	
	public void load(Query<E>  filter);

}
