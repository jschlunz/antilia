/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.test;

import org.hibernate.dialect.DerbyDialect;

import com.antilia.hibernate.cfg.PersistenceUnit;


/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DerbyPersistenceUnit extends PersistenceUnit {

	private  static final DerbyPersistenceUnit instance = new DerbyPersistenceUnit();
	
	/**
	 * @param name
	 */
	private DerbyPersistenceUnit() {
		super("_derby");
		
		setDriverClass(org.apache.derby.jdbc.ClientDriver.class);
		setDialect(DerbyDialect.class);
		setUserName("reiern");
		setPassword("reiern");
		setConnectionUrl("jdbc:derby://localhost:1527/manager");
		setCurrentSessionContextClass(SessionContextClass.thread);
		setUseReflectionOptimizer(false);
		setDefaultSchema(null);
		setTransactionFactoryClass(org.hibernate.transaction.JDBCTransactionFactory.class);
		setShowSql(true);
		setConnectionAutocommit(true);
		
		addPersistenceSet(ManagerPersistenceSet.getInstance());
				
	}

	/**
	 * @return the instance
	 */
	public static DerbyPersistenceUnit getInstance() {
		return instance;
	}

}
