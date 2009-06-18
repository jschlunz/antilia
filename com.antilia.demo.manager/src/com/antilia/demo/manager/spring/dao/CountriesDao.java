/**
 * 
 */
package com.antilia.demo.manager.spring.dao;

import com.antilia.demo.manager.entities.Country;
import com.antilia.hibernate.dao.impl.SpringHibernateQuerableUpdatableDao;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class CountriesDao extends SpringHibernateQuerableUpdatableDao<Country> implements ICountriesDao {

	private static final long serialVersionUID = 1L;

	public CountriesDao() {
	}
}
