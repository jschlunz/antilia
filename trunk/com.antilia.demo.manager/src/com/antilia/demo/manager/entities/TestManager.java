package com.antilia.demo.manager.entities;

import java.util.List;

import com.antilia.demo.manager.test.PostgrePersistenceUnit;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.DefaultCommander;
import com.antilia.hibernate.context.RequestContext;

public class TestManager {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// net.sf.cglib.core.DebuggingClassWriter@1854b38
		// net.sf.cglib.core.DebuggingClassWriter@40627c
		IPersistenceUnit persistenceUnit = PostgrePersistenceUnit.getInstance();		
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);		
		requestContext.setUser("test");
		
		List<Address> addresses = DefaultCommander.loadAll(Address.class);
		for(Address address: addresses) {
			System.err.println(address.getAddress1());
		}
	}

}
