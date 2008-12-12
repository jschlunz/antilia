/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.cfg;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.context.CurrentSessionContext;
import org.hibernate.dialect.Dialect;
import org.hibernate.transaction.TransactionFactory;

import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.util.EntityUtils;


/**
 * Default implementation of IPersistenceUnit...
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public  class  PersistenceUnit implements IPersistenceUnit {
	
	List<Class<?>>  entities = new ArrayList<Class<?>>();
	
	private Map<String, String> properties = new HashMap<String, String>();

	private String name;	
			
	/**
	 * Enumeration for the default supported {@link CurrentSessionContext} classes.
	 * 
	 *
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static enum SessionContextClass {
		thread,
		jta,
		managed
	}
	
	/**
	 * See class {@link org.hibernate.ConnectionReleaseMode} for addional info.
	 * 
	 *
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static enum ConnectionReleaseMode {
		auto,
		after_statement,
		after_transaction,
		on_close,
	}
	
	/**
	 * Enumeration with different possible schema export options
	 *
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static enum AutoSchemaExport {
		validate("validate"),
		update("update"),
		create("create"),
		create_drop("create-drop");
		
		private String id;
		
		AutoSchemaExport(String id) {
			this.id = id;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
	}
	
	public PersistenceUnit(String name) {
		this.name = name;
		// setting session context class to thread..
		setCurrentSessionContextClass(SessionContextClass.thread);
		// setting auto as the default coonection releease mode.
		setConnectionReleaseMode(ConnectionReleaseMode.auto);
	}
	
	public AnnotationConfiguration createConfiguration() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		for(Class<?> clazz: entities)
			configuration.addAnnotatedClass(clazz);
		
		for(String property: properties.keySet()) {
			String  value = properties.get(property);
			if(!StringUtils.isEmpty(value)) 
				configuration.setProperty(property, value);
		}		
		return configuration;
	}
	
	
	public PersistenceUnit addEntityClass(Class<?> entityClass) {
		if(EntityUtils.isEntity(entityClass))
			entities.add(entityClass);
		return this;
	}
	
	@Override
	public Iterable<Class<?>> getEntityClasses() {
		return entities;
	}
	
	public PersistenceUnit addEntitiesInSamePackageAs(Class<?> entityClass) {
		EntityUtils.addEntitiesInSamePackageAs(entityClass, this);
		return this;
	}
	
	public PersistenceUnit removeEntity(Class<?> entityClass) {		
		entities.remove(entityClass);
		return  this;
	}
		
	public PersistenceUnit addProperty(String name, String value) {
		properties.put(name, value);
		return this;
	}
	
	public IPersistenceUnit addPersistenceSet(IPersistenceSet entitiesSet) {
		for(Class<?> entityClass: entitiesSet.getEntityClasses()) {
			addEntityClass(entityClass);
		}
		return this;
	}
	
	/**
	 * The 
	 * @return
	 */
	public PersistenceUnit setDialect(Class<? extends Dialect> dialectClass) {
		properties.put(Environment.DIALECT, dialectClass.getName());
		return this;
	}
	
	/**
	 * The driver class 
	 * @return
	 */
	public PersistenceUnit setDriverClass(Class<? extends Driver> clazz) {
		properties.put(Environment.DRIVER, clazz.getName());
		return this;
	}
	
	/**
	 * Sets the connection URL...
	 * 
	 * @param connectionUrl
	 * @return
	 */
	public PersistenceUnit setConnectionUrl(String  connectionUrl){
		properties.put(Environment.URL, connectionUrl);
		return this;
	}
	
	/**
	 * Sets the database user name.
	 * @param userName
	 * @return
	 */
	public PersistenceUnit setUserName(String  userName){
		properties.put(Environment.USER, userName);
		return this;
	}	
	
	/**
	 * Sets the password (for the database user).
	 * 
	 * @param password
	 * @return
	 */
	public PersistenceUnit setPassword(String  password){
		properties.put(Environment.PASS, password);
		return this;
	}
	
	public PersistenceUnit setConnectionAutocommit(boolean autocommit) {
		properties.put(Environment.AUTOCOMMIT, Boolean.toString(autocommit));
		return this;
	}
	
	/**
	 * Sets the CurrentSessionContextClass to one of the three supported defaults.
	 * 
	 * @param sessionContextClass
	 * @return
	 */
	public PersistenceUnit setCurrentSessionContextClass(SessionContextClass sessionContextClass) {
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, sessionContextClass.name());
		return this;
	}
	
	/**
	 * Set your own implementation of CurrentSessionContext..
	 * 
	 * @param sessionContextClass
	 * @return
	 */
	public PersistenceUnit setCurrentSessionContextClass(Class<? extends CurrentSessionContext> sessionContextClass) {
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, sessionContextClass.getName());
		return this;
	}

	public PersistenceUnit setUseReflectionOptimizer(boolean useReflectionOptimizer) {
		properties.put(Environment.USE_REFLECTION_OPTIMIZER, Boolean.toString(useReflectionOptimizer));
		return this;
	}
	
	public PersistenceUnit setDefaultSchema(String defaultSchema) {
		properties.put(Environment.DEFAULT_SCHEMA, defaultSchema);
		return this;
	}
	
	public PersistenceUnit setTransactionFactoryClass(Class<? extends TransactionFactory> transactionFactoryClass) {
		properties.put(Environment.TRANSACTION_STRATEGY , transactionFactoryClass.getName());
		return this;
	}
	
	/**
	 * Flag setting whether SQL statement should be logged or not.
	 * @param showSql
	 * @return
	 */
	public PersistenceUnit  setShowSql(boolean showSql) {
		properties.put(Environment.SHOW_SQL , Boolean.toString(showSql));
		return this;
	}
	
	/**
	 * Flag setting whether logged  SQL statement should be formated or not.
	 * @param formatSql
	 * @return
	 */
	public PersistenceUnit  setFormatSql(boolean formatSql) {
		properties.put(Environment.FORMAT_SQL , Boolean.toString(formatSql));
		return this;
	}
	
	
	public PersistenceUnit  setConnectionReleaseMode(ConnectionReleaseMode connectionReleaseMode) {
		properties.put(Environment.RELEASE_CONNECTIONS, connectionReleaseMode.name());
		return this;
	}
			
	/**
	 * Use this property with caution as it can alter the database: normally you don't have to set it at all...
	 * 
	 * @param autoSchemaExport
	 * @return
	 */
	public PersistenceUnit setAutoSchemaExport(AutoSchemaExport autoSchemaExport) {
		properties.put(Environment.HBM2DDL_AUTO, autoSchemaExport.getId());
		return this;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	public void setConnectionProviderClass(Class<? extends ConnectionProvider> connectionProviderClass) {
		properties.put(Environment.CONNECTION_PROVIDER, connectionProviderClass.getName());
	}
	
	
}
