/**
 * 
 */
package com.antilia.hibernate.query.transform.impl;

import org.hibernate.criterion.Criterion;

import com.antilia.common.query.IFilter;
import com.antilia.hibernate.query.transform.IFilterTransformer;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface ITransformerLocator {

	IFilterTransformer<Criterion> getTransformer(IFilter filter);
	
}
