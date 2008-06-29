package com.antilia.hibernate.command;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.antilia.hibernate.cfg.IPersistenceUnit;

public class HibernateUtil {
	
	private static Map<String, SessionFactory> sessionFactories = new HashMap<String, SessionFactory>();
	
	
	public static SessionFactory getSessionFactory(IPersistenceUnit persistenceUnit) {
		SessionFactory sessionFactory = sessionFactories.get(persistenceUnit.getName());
		if(sessionFactory == null) {
			sessionFactory = persistenceUnit.createConfiguration().buildSessionFactory();
			sessionFactories.put(persistenceUnit.getName(), sessionFactory);
		}
		return sessionFactory;
	}
	
	public static void shutdown(IPersistenceUnit persistenceUnit) {
		getSessionFactory(persistenceUnit).close();
	}

}
