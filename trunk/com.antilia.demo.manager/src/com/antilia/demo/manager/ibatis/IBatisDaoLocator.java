/**
 * 
 */
package com.antilia.demo.manager.ibatis;

import org.apache.wicket.injection.web.InjectorHolder;

import com.antilia.ibatis.dao.IBatisAggregatedDaoLocator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class IBatisDaoLocator extends IBatisAggregatedDaoLocator {
	
	private static IBatisDaoLocator instance;

	
	/**
	 * 
	 */
	private IBatisDaoLocator() {				
	}
	
	@Override
	protected void initialize() {
		InjectorHolder.getInjector().inject(this);
	}

	/**
	 * @return the instance
	 */
	public static IBatisDaoLocator getInstance() {
		if(instance== null) {
			instance = new IBatisDaoLocator();
		}
		return instance;
	}
}
