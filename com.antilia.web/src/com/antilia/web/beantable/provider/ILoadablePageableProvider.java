/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ILoadablePageableProvider<E extends Serializable> extends IPageableProvider<E>, ILoadable<E> {

}
