package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name = "country", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Country implements java.io.Serializable, Comparable<Country> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator="country_seq")
	@SequenceGenerator(name="country_seq",sequenceName="country_seq", allocationSize=1)
	//@GeneratedValue(strategy=GenerationType.IDENTITY, generator="country_seq")
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false, length = 300)
	private String name;
	@Column(name = "domain", nullable = false, length = 3)
	private String domain;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private Set<City> cities = new HashSet<City>(0);

	public Country() {
	}

	public Country(long id, String name, String domain) {
		this.id = id;
		this.name = name;
		this.domain = domain;
	}

	public Country(long id, String name, String domain, Set<City> cities) {
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.cities = cities;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Country addCity(City city) {
		cities.add(city);
		city.setCountry(this);
		return this;
	}
	
	public Country addCity(String name) {
		return addCity(new City(name));
	}
	
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	@Override
	public int compareTo(Country o) {
		return getName().compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Country) {
			return name.equals(((Country)obj).getName());
		}
		return false;
	}
}
