package com.antilia.wstarter.demo;

import org.apache.wicket.protocol.http.WebApplication;

import com.antilia.wstarter.WebApplicationActivator;


public class Activator extends WebApplicationActivator {

	public Activator(){
		
	}
	
	@Override
	protected Class<? extends WebApplication> getApplicationClass() {
		return DemoApplication.class;
	}
	
	@Override
	protected String getServletAlias() {
		return "demo-app";
	}

}
