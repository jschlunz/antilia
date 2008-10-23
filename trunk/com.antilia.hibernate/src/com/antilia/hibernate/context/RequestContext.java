/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.context;

import org.hibernate.Session;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.HibernateUtil;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RequestContext {
	
	private String user;
	
	private IPersistenceUnit persistenceUnit;
	
	private IProgressReporter progressReporter;

	private Tier tier;
	
	private static final ThreadLocal<RequestContext> current = new ThreadLocal<RequestContext>();

	private RequestContext() {		
		setTier(Tier.PRESENTATION);
	}
	
	public static RequestContext get() {		
		RequestContext requestContext = current.get();
		if(requestContext == null) {
			requestContext = new RequestContext();			
			current.set(requestContext);
		}		
		return requestContext;
 	}
	
	public static void unget() {
		current.remove();
	}
	
	
	public Session getSession() {
		return HibernateUtil.getSessionFactory(getPersistenceUnit()).getCurrentSession();
	}

	/**
	 * @return the persistenceUnit
	 */
	public IPersistenceUnit getPersistenceUnit() {
		return persistenceUnit;
	}

	/**
	 * @param persistenceUnit the persistenceUnit to set
	 */
	public void setPersistenceUnit(IPersistenceUnit persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}

	public IProgressReporter getProgressReporter() {
		return progressReporter;
	}

	public void setProgressReporter(IProgressReporter progressReporter) {
		this.progressReporter = progressReporter;
	}
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the tier
	 */
	public Tier getTier() {
		return tier;
	}

	/**
	 * @param tier the tier to set
	 */
	public void setTier(Tier tier) {
		this.tier = tier;
	}
}
