/**
 * 
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableUpdatebleDataProvider<E extends Serializable> extends IQuerableDataProvider<E>, IUpdatable<E> {

}
