/**
 * 
 */
package com.antilia.demo.manager.ibatis;

import java.io.Serializable;

import com.antilia.ibatis.Table;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
@Table(name="country")
public class IBCountry implements Serializable {

	private static final long serialVersionUID = 1L;

	@com.antilia.ibatis.Column(name = "id", defaultorder=true)
	private Long id;
	
	@com.antilia.ibatis.Column(name = "name")
	private String name;
	
	@com.antilia.ibatis.Column(name = "domain")
	private String domain;
	
	/**
	 * 
	 */
	public IBCountry() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

}
