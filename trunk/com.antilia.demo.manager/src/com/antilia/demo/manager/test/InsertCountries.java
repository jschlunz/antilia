/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.test;

import java.io.Serializable;

import com.antilia.demo.manager.entities.Country;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.command.AbstractPersistentCommand;
import com.antilia.hibernate.command.CommandExecuter;
import com.antilia.hibernate.context.RequestContext;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InsertCountries {

	private static class PersistCountriesCommand extends AbstractPersistentCommand<Country, Serializable> {
		
		public PersistCountriesCommand() {
			super(Country.class);
		}
		
		@Override
		protected Serializable doExecute() throws Throwable {			
			CommandExecuter.persist(createCuba());
			CommandExecuter.persist(createSpain());
			CommandExecuter.persist(createGermany());
			CommandExecuter.persist(createTheNederlands());
			CommandExecuter.persist(createFrance());
			return "Success";
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IPersistenceUnit persistenceUnit = PostgrePersistenceUnit.getInstance();		
		RequestContext requestContext = RequestContext.get();
		requestContext.setPersistenceUnit(persistenceUnit);
		requestContext.setUser("test");
		
		
		try {	
			CommandExecuter.execute(new PersistCountriesCommand());
		} catch (Throwable e) {
			e.printStackTrace();
		}
				
	}
	
	private static Country createCuba() {
		Country cuba = new Country();
		cuba.setDomain("cu");
		cuba.setName("Cuba");	
		
		cuba.addCity("Pinar del Rio")
		.addCity("Baracoa")
		.addCity("San Juan de los Remedios")
		.addCity("San Cristobal")
		.addCity("Quemado de Guines")
		.addCity("Artemisa")
		.addCity("Bahia")
		.addCity("Palma Soriano")
		.addCity("Cuidad Habana")
		.addCity("CienFuegos")
		.addCity("Matanzas")
		.addCity("Holguin")
		.addCity("Camaguey")
		.addCity("Santa Clara")
		.addCity("Las Tunas")
		.addCity("Santi Spiritus")	
		.addCity("Santiago de Cuba");
		return cuba;
	}
	
	private static Country createSpain() {
		Country spain = new Country();
		spain.setDomain("es");
		spain.setName("Spain");	
		
		spain.addCity("Barcelona")
		.addCity("Mataro")
		.addCity("Madrid")
		.addCity("Calatayud")
		.addCity("Panplona")
		.addCity("Zaragoza")
		.addCity("Bigo")
		.addCity("Burgos")
		.addCity("Benidorm")
		.addCity("Oviedo")
		.addCity("Mondragon")
		.addCity("Sna Sebastian")
		.addCity("Girona")
		.addCity("Valencia")
		.addCity("Toledo")
		.addCity("Salamanca")
		.addCity("Ceuta")
		.addCity("Melilla")
		.addCity("Tarragona")
		.addCity("Cordoba")
		.addCity("Cadiz");
		return spain;
	}

	private static Country createGermany() {
		Country germany = new Country();
		germany.setDomain("de");
		germany.setName("Germany");	
		
		germany.addCity("Frankfurt")
		.addCity("Wuppertahl")
		.addCity("Muenchen")
		.addCity("Wiesbaden")
		.addCity("Henselkirschen")
		.addCity("Berlin")
		.addCity("Leverkussen")
		.addCity("Blumentahl")
		;
		return germany;
	}
	
	private static Country createTheNederlands() {
		Country nederlands = new Country();
		nederlands.setDomain("nl");
		nederlands.setName("The Nederlands");	
		
		nederlands.addCity("Den Haag")
		.addCity("Amsterdam")
		.addCity("Eindhoven")
		.addCity("Groningen")
		.addCity("Utrech")
		.addCity("Roterdam")
		.addCity("Leuven")
		.addCity("Leiden")
		;
		return nederlands;
	}
	
	private static Country createFrance() {
		Country france = new Country();
		france.setDomain("fr");
		france.setName("France");	
		
		france.addCity("Paris")
		.addCity("Montpelier")
		.addCity("Burdeaux")
		.addCity("Maseille")
		.addCity("Touluse")
		.addCity("Perpignan");
		return france;
	}
}
