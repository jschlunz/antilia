package com.antilia.demo.manager.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.command.AbstractPersistentCommand;
import com.antilia.hibernate.command.DefaultCommander;

public class InsertData extends AbstractPersistentCommand<Country, Serializable> {

	public InsertData() {
		super(Country.class);
	}
	
	@Override
	protected Serializable doExecute() throws Throwable {
		List<Country> countries = new ArrayList<Country>();		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(InsertData.class.getResourceAsStream("countries.txt")));
			String line = null;
			do {
				line =reader.readLine();
				Country country = new Country();				
				if(!StringUtils.isEmpty(line)) {	
					line = line.trim();
					StringTokenizer st = new StringTokenizer(line,"|");
					country.setDomain(st.nextToken());
					country.setName(st.nextToken());					
					countries.add(country);
				}
			} while(line != null);
			
			for(Country country: countries) {
				System.err.println(country.getName()+","+country.getDomain());
			}
			
			for(Country country: countries) {
				addCities(country);
				DefaultCommander.persist(country);
				addAddresses(country);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	private void addCities(Country country) {
		for(int i = 0; i < 10;i++) {
			City city = new City();
			city.setName("City"+i);			
			country.addCity(city);
		}
	}
	
	private void addAddresses(Country country) {
		List<Address> addresses = new ArrayList<Address>();
		for(City city: country.getCities()) {			
			for(int i = 0; i < 5;i++) {
				Address address = new Address();
				address.setCity(city);
				address.setAddress1("Street XXX-FFF dDSDDDD  DDDdd ddd Eeee Eee"+i);
				address.setZipcode("XXZA"+i);
				addresses.add(address);
			}
		}		
		DefaultCommander.persistAll(addresses);
	}
	
}
