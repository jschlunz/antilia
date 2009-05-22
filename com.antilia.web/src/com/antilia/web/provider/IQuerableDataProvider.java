/**
 * 
 */
package com.antilia.web.provider;

import java.io.Serializable;

import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * A data provider that can be set a query.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableDataProvider<E extends Serializable> extends IDataProvider<E>, IQuerable<E> {

}
