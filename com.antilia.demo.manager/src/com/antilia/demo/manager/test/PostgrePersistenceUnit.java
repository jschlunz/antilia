/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.test;

import org.hibernate.dialect.PostgreSQLDialect;
import org.postgresql.Driver;

import com.antilia.hibernate.cfg.PersistenceUnit;


/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PostgrePersistenceUnit extends PersistenceUnit {

	private  static final PostgrePersistenceUnit instance = new PostgrePersistenceUnit();
	
	/**
	 * @param name
	 */
	private PostgrePersistenceUnit() {
		super("_remoteInformix");
		
		setDriverClass(Driver.class);
		setDialect(PostgreSQLDialect.class);
		setUserName("reiern70");
		setPassword("tetris11");
		setConnectionUrl("jdbc:postgresql://localhost:5432/manager");
		setCurrentSessionContextClass(SessionContextClass.thread);
		setUseReflectionOptimizer(false);
		setDefaultSchema("public");
		setTransactionFactoryClass(org.hibernate.transaction.JDBCTransactionFactory.class);
		setShowSql(true);
		setConnectionAutocommit(true);
		
		addPersistenceSet(ManagerPersistenceSet.getInstance());
				
	}

	/**
	 * @return the instance
	 */
	public static PostgrePersistenceUnit getInstance() {
		return instance;
	}

}
