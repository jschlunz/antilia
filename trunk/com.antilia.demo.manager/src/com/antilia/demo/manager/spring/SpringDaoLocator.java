/**
 * 
 */
package com.antilia.demo.manager.spring;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.antilia.demo.manager.entities.Country;
import com.antilia.demo.manager.spring.dao.ICountriesDao;
import com.antilia.hibernate.dao.impl.SpringAggregatedDaoLocator;
import com.google.inject.Inject;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SpringDaoLocator extends SpringAggregatedDaoLocator {
	
	private static SpringDaoLocator instance;
	
	@SpringBean
	@Inject
	private ICountriesDao countriesDao;
	
	
	/**
	 * 
	 */
	private SpringDaoLocator() {				
	}
	
	@Override
	protected void initialize() {
		InjectorHolder.getInjector().inject(this);
		registerLocator(Country.class, countriesDao);		
	}

	/**
	 * @return the instance
	 */
	public static SpringDaoLocator getInstance() {
		if(instance== null) {
			instance = new SpringDaoLocator();
		}
		return instance;
	}
}
