/**
 * 
 */
package com.antilia.web.beantable.provider;

import java.io.Serializable;

import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IQuerableDataProvider<E extends Serializable> extends IDataProvider<E>, IQuerable<E> {

}
