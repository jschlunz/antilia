package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project", uniqueConstraints = @UniqueConstraint(columnNames = {
		"name", "customer" }))
public class Project implements java.io.Serializable {

	private long id;
	private Customer customer;
	private String name;
	private String description;
	private Date starttime;
	private Date endtime;

	public Project() {
	}

	public Project(long id, Customer customer, String name, Date starttime) {
		this.id = id;
		this.customer = customer;
		this.name = name;
		this.starttime = starttime;
	}

	public Project(long id, Customer customer, String name, String description,
			Date starttime, Date endtime) {
		this.id = id;
		this.customer = customer;
		this.name = name;
		this.description = description;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "starttime", nullable = false, length = 13)
	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endtime", length = 13)
	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

}