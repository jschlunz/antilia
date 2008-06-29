/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.guice;

import com.antilia.hibernate.transaction.Transactional;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bindInterceptor(Matchers.not(Matchers.any()), Matchers.annotatedWith(Transactional.class), new TransactionMethodInterceptor());
	}
	

}
