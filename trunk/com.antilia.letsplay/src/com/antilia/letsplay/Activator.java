package com.antilia.letsplay;

import org.apache.wicket.protocol.http.WebApplication;

import com.antilia.web.osgi.WebApplicationActivator;


public class Activator extends WebApplicationActivator {

	public Activator() {			
		
	}
	
	@Override
	protected Class<? extends WebApplication> getApplicationClass() {
		return PlayApplication.class;
	}
	
	@Override
	protected String getServletAlias() {
		return "letsplay";
	}
}
