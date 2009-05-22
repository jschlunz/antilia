/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.navigator;

import java.io.Serializable;

import com.antilia.web.provider.ILoadable;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ILoadablePageableNavigator<E extends Serializable> extends IPageableNavigator<E>, ILoadable<E> {

}
