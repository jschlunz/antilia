/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.letsplay.domain;

import org.hibernate.dialect.HSQLDialect;

import com.antilia.hibernate.cfg.PersistenceUnit;


/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HSQLDBPersistenceUnit extends PersistenceUnit {

	private  static final HSQLDBPersistenceUnit instance = new HSQLDBPersistenceUnit();
	
	/**
	 * @param name
	 */
	private HSQLDBPersistenceUnit() {
		super("_derby");
		
		setDriverClass(org.hsqldb.jdbcDriver.class);
		setDialect(HSQLDialect.class);
		setUserName("sa");
		setPassword("");
		setConnectionUrl("jdbc:hsqldb:mem:manager");
		setCurrentSessionContextClass(SessionContextClass.thread);
		setUseReflectionOptimizer(false);
		setDefaultSchema(null);
		setTransactionFactoryClass(org.hibernate.transaction.JDBCTransactionFactory.class);
		setShowSql(true);
		setConnectionAutocommit(true);
		setProperty("hibernate.connection.pool_size", "1").
		setProperty("hibernate.connection.autocommit", "true").
		setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider").
		setProperty("hibernate.hbm2ddl.auto", "create-drop");
         
		addPersistenceSet(ManagerPersistenceSet.getInstance());
				
	}

	/**
	 * @return the instance
	 */
	public static HSQLDBPersistenceUnit getInstance() {
		return instance;
	}

}
