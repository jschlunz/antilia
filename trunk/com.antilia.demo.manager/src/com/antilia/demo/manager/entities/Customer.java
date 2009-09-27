package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.antilia.web.crud.Exclude;
import com.antilia.web.field.impl.DrillInSelectionMode;
import com.antilia.web.field.impl.SelectionType;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", 
		uniqueConstraints = @UniqueConstraint(columnNames = {
		"name", "address" }))
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Exclude
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address", nullable = false)
	@SelectionType(type=DrillInSelectionMode.LARGE_IN_MODAL_DIALOG)
	private Address address;
	
	@Column(name = "name", nullable = false, length = 90)
	private String name;

	@Column(name = "status", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	@Column(name = "registered", nullable = false, length = 8)
	private Date registered;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Project> projects = new HashSet<Project>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Chargerate> chargerates = new HashSet<Chargerate>(0);

	public Customer() {
	}

	public Customer(Long id, Address address, String name) {
		this.id = id;
		this.address = address;
		this.name = name;
	}

	public Customer(Long id, Address address, String name, CustomerStatus status,
			Set<Project> projects, Set<Chargerate> chargerates) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.status = status;
		this.projects = projects;
		this.chargerates = chargerates;
	}

	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public CustomerStatus getStatus() {
		return this.status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public void addChargerate(Project project) {
		this.projects.add(project);
		project.setCustomer(this);
	}
	
	public void removeChargerate(Project project) {
		this.projects.remove(project);
		project.setCustomer(null);
	}
	
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	
	public void addChargerate(Chargerate chargerate) {
		this.chargerates.add(chargerate);
		chargerate.setCustomer(this);
	}
	
	public void removeChargerate(Chargerate chargerate) {
		this.chargerates.remove(chargerate);
		chargerate.setCustomer(null);
	}
	
	public Set<Chargerate> getChargerates() {
		return this.chargerates;
	}

	public void setChargerates(Set<Chargerate> chargerates) {
		this.chargerates = chargerates;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

}
