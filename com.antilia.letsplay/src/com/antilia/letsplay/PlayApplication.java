/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.letsplay;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.Session;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.letsplay.domain.HSQLDBPersistenceUnit;
import com.antilia.letsplay.domain.InsertData;
import com.antilia.web.AntiliaWebApplication;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PlayApplication extends AntiliaWebApplication {

	/*
	private static final DisableAllAuthorizationStrategy AUTORIZATION_STRATEGY = new DisableAllAuthorizationStrategy() {
		
		public java.lang.Class<? extends org.apache.wicket.markup.html.WebPage> getSignInPage() {
			return LoginPage.class;
		};
	};
	*/

	
	public PlayApplication() {
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return Index.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new PlaySession(request);
	}
	
	@Override
	protected void init() {
		super.init();
		
		
		//getSecuritySettings().setUnauthorizedComponentInstantiationListener(AUTORIZATION_STRATEGY);
		//getSecuritySettings().setAuthorizationStrategy(AUTORIZATION_STRATEGY);
	
		getApplicationSettings().setAccessDeniedPage(LoginPage.class);
		getApplicationSettings().setPageExpiredErrorPage(LoginPage.class);
		
		getMarkupSettings().setStripWicketTags(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getDebugSettings().setOutputMarkupContainerClassName(false);
		initializeDatabase();				
	}
	
	private void initializeDatabase() {						
		IPersistenceUnit persistenceUnit = HSQLDBPersistenceUnit.getInstance();
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);
		requestContext.setUser("test");
		DefaultCommander.execute(new InsertData());
	}
	
	@Override
	public RequestCycle newRequestCycle(Request request, Response response) {
		IPersistenceUnit persistenceUnit = HSQLDBPersistenceUnit.getInstance();
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);
		requestContext.setUser("test");
		return super.newRequestCycle(request, response);
	}
}
