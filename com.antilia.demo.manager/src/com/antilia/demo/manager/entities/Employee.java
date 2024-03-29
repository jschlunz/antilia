package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.antilia.web.crud.Exclude;
import com.antilia.web.field.impl.DrillInSelectionMode;
import com.antilia.web.field.impl.SelectionType;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Exclude
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "homeaddress", nullable = false)
	@SelectionType(type=DrillInSelectionMode.LARGE_ON_NEXT_PAGE)
	private Address address;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "lastname1", nullable = false, length = 100)
	private String lastname1;
	
	@Column(name = "lastname2", length = 100)
	private String lastname2;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "hired", nullable = false, length = 13)
	private Date hired;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fired", length = 13)
	private Date fired;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<EmployeeRole> employeeRoles = new HashSet<EmployeeRole>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<Asignment> asignments = new HashSet<Asignment>(0);

	public Employee() {
	}

	public Employee(long id, Address address, String name, String lastname1,
			Date hired) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.lastname1 = lastname1;
		this.hired = hired;
	}

	public Employee(long id, Address address, String name, String lastname1,
			String lastname2, Date hired, Date fired,
			Set<EmployeeRole> employeeRoles, Set<Asignment> asignments) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.lastname1 = lastname1;
		this.lastname2 = lastname2;
		this.hired = hired;
		this.fired = fired;
		this.employeeRoles = employeeRoles;
		this.asignments = asignments;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	
	public String getLastname1() {
		return this.lastname1;
	}

	public void setLastname1(String lastname1) {
		this.lastname1 = lastname1;
	}

	
	public String getLastname2() {
		return this.lastname2;
	}

	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}


	public Date getHired() {
		return this.hired;
	}

	public void setHired(Date hired) {
		this.hired = hired;
	}

	
	public Date getFired() {
		return this.fired;
	}

	public void setFired(Date fired) {
		this.fired = fired;
	}

	
	public Set<EmployeeRole> getEmployeeRoles() {
		return this.employeeRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hired == null) ? 0 : hired.hashCode());
		result = prime * result
				+ ((lastname2 == null) ? 0 : lastname2.hashCode());
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
		Employee other = (Employee) obj;
		if (hired == null) {
			if (other.hired != null)
				return false;
		} else if (!hired.equals(other.hired))
			return false;
		if (lastname2 == null) {
			if (other.lastname2 != null)
				return false;
		} else if (!lastname2.equals(other.lastname2))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

	
	public Set<Asignment> getAsignments() {
		return this.asignments;
	}

	public void setAsignments(Set<Asignment> asignments) {
		this.asignments = asignments;
	}

}
