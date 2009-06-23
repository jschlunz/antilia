package com.antilia.common.dao;

import java.io.Serializable;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 * @param <E>
 */
public interface IQuerableUpdatableDao<E extends Serializable> extends IUpdatableDao<E>, IQuerableDao<E>  {

}
