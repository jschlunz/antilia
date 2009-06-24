/**
 * 
 */
package com.antilia.hibernate.query.transform;

import org.hibernate.criterion.Criterion;

import com.antilia.common.query.IRestriction;
import com.antilia.common.query.transform.IRestrictionTransformer;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface ITransformerLocator {

	IRestrictionTransformer<Criterion> getTransformer(IRestriction filter);
	
}
