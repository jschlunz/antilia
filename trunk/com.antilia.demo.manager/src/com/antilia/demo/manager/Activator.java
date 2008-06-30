package com.antilia.demo.manager;

import com.antilia.web.WebApplication;
import com.antilia.web.osgi.WebApplicationActivator;

public class Activator extends WebApplicationActivator {

	public Activator() {		
	}
	
	@Override
	protected Class<? extends WebApplication> getApplicationClass() {
		return ManagerApplication.class;
	}
	
	@Override
	protected String getServletAlias() {
		return "manager";
	}
	
}
