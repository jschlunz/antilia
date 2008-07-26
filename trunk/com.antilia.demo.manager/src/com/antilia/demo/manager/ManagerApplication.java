/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.Session;

import com.antilia.demo.manager.test.PostgrePersistenceUnit;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.web.WebApplication;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ManagerApplication extends WebApplication {

	public ManagerApplication() {
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<?> getHomePage() {
		return Index.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new ManagerSession(request);
	}
	
	@Override
	protected void init() {
		super.init();
		
		getMarkupSettings().setStripWicketTags(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getDebugSettings().setOutputMarkupContainerClassName(false);
	}
	
	@Override
	public RequestCycle newRequestCycle(Request request, Response response) {
		IPersistenceUnit persistenceUnit = PostgrePersistenceUnit.getInstance();		
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);
		requestContext.setUser("test");
		return super.newRequestCycle(request, response);
	}
}
