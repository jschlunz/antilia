package com.antilia.demo;

import com.antilia.web.AntiliaWebApplication;
import com.antilia.web.osgi.WebApplicationActivator;

public class Activator extends WebApplicationActivator {

	public Activator(){
		
	}
	
	@Override
	protected Class<? extends AntiliaWebApplication> getApplicationClass() {
		return DemoApplication.class;
	}
	
	@Override
	protected String getServletAlias() {
		return "demo";
	}

}
