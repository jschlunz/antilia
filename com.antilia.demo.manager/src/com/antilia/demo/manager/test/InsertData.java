/**
 * 
 */
package com.antilia.demo.manager.test;

import java.util.List;

import com.antilia.demo.manager.entities.City;
import com.antilia.demo.manager.entities.Country;
import com.antilia.hibernate.command.AbstractPersistentCommand;
import com.antilia.hibernate.command.CommandExecuter;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InsertData extends AbstractPersistentCommand<Country, Boolean> {

	public InsertData() {
		super(Country.class);
	}
	
	@Override
	protected Boolean doExecute() throws Throwable {
		Country country = new Country();
		country.setName("Cuba");
		country.setDomain("cu");		
		CommandExecuter.persist(country);
		return Boolean.TRUE;
	}
	
	private void persistCubanCities(Country country) {
		City city = new City();
		city.setName("");
		
	}
	
	private List<City> readFromFile() {
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommandExecuter.execute(new InsertData());
	}

}
