/**
 * 
 */
package com.antilia.web.provider;

import java.io.Serializable;

/**
 * A data provider that besides been query aware can save or update elements.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableUpdatebleDataProvider<E extends Serializable> extends IQuerableDataProvider<E>, IUpdatable<E> {

}
