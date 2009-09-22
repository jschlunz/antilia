/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.antilia.common.util.StringUtils;
import com.antilia.demo.manager.spring.SpringHibernateInitializer;
import com.antilia.demo.manager.test.HSQLDBPersistenceUnit;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.web.AntiliaWebApplication;
import com.antilia.web.login.DisableAllAuthorizationStrategy;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ManagerApplication extends AntiliaWebApplication {

	private static final DisableAllAuthorizationStrategy AUTORIZATION_STRATEGY = new DisableAllAuthorizationStrategy() {
		
		public java.lang.Class<? extends org.apache.wicket.markup.html.WebPage> getSignInPage() {
			return LoginPage.class;
		};
	};
	
	public static enum InjectionEngine {
		GUICE,
		SPRING,
		SPRING_IBATIS
	}
	
	private InjectionEngine injectionEngine;
	
	public ManagerApplication() {
		String ie = System.getProperty("com.antilia.manager.injectionEngine");
		if(StringUtils.isEmpty(ie) || ie.equalsIgnoreCase(InjectionEngine.GUICE.toString()))
			injectionEngine = InjectionEngine.GUICE;
		else if(ie.equalsIgnoreCase(InjectionEngine.SPRING.toString()))
			injectionEngine = InjectionEngine.SPRING;
		else 
			injectionEngine = InjectionEngine.SPRING_IBATIS;	
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return Index.class;
		//return TestTablePage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new ManagerSession(request);
	}
	
	@Override
	protected void init() {
		super.init();
		
		
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(AUTORIZATION_STRATEGY);
		getSecuritySettings().setAuthorizationStrategy(AUTORIZATION_STRATEGY);
	
		getApplicationSettings().setAccessDeniedPage(LoginPage.class);
		getApplicationSettings().setPageExpiredErrorPage(LoginPage.class);
		
		getMarkupSettings().setStripWicketTags(true);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getDebugSettings().setOutputMarkupContainerClassName(false);
		if(injectionEngine.equals(InjectionEngine.SPRING)) {
			initializeSpring();
		}
	}
	
	private void initializeSpring() {						
		addComponentInstantiationListener(new SpringComponentInjector(this, SpringHibernateInitializer.getInstance().intialize()));
	}
	
	@Override
	public RequestCycle newRequestCycle(Request request, Response response) {
		//IPersistenceUnit persistenceUnit = PostgrePersistenceUnit.getInstance();
		if(injectionEngine.equals(InjectionEngine.GUICE)) {
			IPersistenceUnit persistenceUnit = HSQLDBPersistenceUnit.getInstance();
			RequestContext requestContext = RequestContext.get();
			requestContext.setPersistenceUnit(persistenceUnit);
			requestContext.setUser("test");
		}
		return super.newRequestCycle(request, response);
	}
}
