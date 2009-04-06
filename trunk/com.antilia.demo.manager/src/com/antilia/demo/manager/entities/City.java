package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.antilia.web.crud.Exclude;
import com.antilia.web.field.impl.SelectionMode;
import com.antilia.web.field.impl.SelectionType;

/**
 * City generated by hbm2java
 */
@Entity
@Table(name = "city", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "country" }))
public class City implements java.io.Serializable, Comparable<City>{

	private static final long serialVersionUID = 1L;

	@Exclude
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name="city_seq",sequenceName="city_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country", nullable = false)
	@SelectionType(type=SelectionMode.LARGE_ON_NEXT_PAGE)
	private Country country;

	@Column(name = "name", nullable = false, length = 300)
	private String name;
	
	public City() {
	}
	
	public City(String name) {
		this.name = name;
	}
	
	public City(String name, Country country) {
		this.country = country;
		this.name = name;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int compareTo(City o) {
		int country = getCountry().compareTo(o.getCountry());
		if(country == 0)
			return getName().compareTo(o.getName());
		return country;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof City) {
			City city = (City)obj;
			return name.equals(city.getName()) && getCountry().equals(city.getCountry());
		}
		return false;
	}		
	
	@Override
	public String toString() {
		return getName() + " ("+getCountry().getName()+")";
	}
}
