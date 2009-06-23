/**
 * 
 */
package com.antilia.hibernate.dao.impl;

import com.antilia.common.dao.IDaoLocator;
import com.antilia.common.dao.impl.AggregatedDaoLocator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class DefaultAggregatedDaoLocator extends AggregatedDaoLocator {
	
	protected DefaultAggregatedDaoLocator() {		
	}	

	@Override
	protected IDaoLocator getDefaultDaoLocator() {
		return DefaultDaoLocator.getInstance();
	}
	
}
