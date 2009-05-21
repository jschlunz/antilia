/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.test;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestHibernatePageableProvider {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		IPersistenceUnit persistenceUnit = PostgrePersistenceUnit.getInstance();		
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);
		requestContext.setUser("test");
		
		/*
		Query<Country> query = new Query<Country>(Country.class);
		HibernatePageableProvider<Country> hibernatePageableProvider = new HibernatePageableProvider<Country>(query);
		Iterator<Country> it = hibernatePageableProvider.getCurrentPage(); 
		while(it.hasNext()) {
			Country country = it.next();
			System.out.println(country.getName());
			for(City city: country.getCities()) {
				System.out.println(country.getName() + ">"+city.getName());
			}
		}
		
		System.out.println("..........................");

		Query<City> query2 = new Query<City>(City.class);
		HibernatePageableProvider<City> hibernatePageableProvider2 = new HibernatePageableProvider<City>(query2);
		Iterator<City> it2 = hibernatePageableProvider2.getCurrentPage();
	
		while(it2.hasNext()) {
			City city = it2.next();
			System.out.println(city.getName());
		}
		
		it2 = hibernatePageableProvider2.nextPage();
		
		while(it2.hasNext()) {
			City city = it2.next();
			System.out.println(city.getName());
		}
		*/
	}

}
