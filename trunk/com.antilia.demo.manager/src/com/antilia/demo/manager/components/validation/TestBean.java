package com.antilia.demo.manager.components.validation;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Past;
import org.hibernate.validator.Range;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class TestBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;

	@NotNull
	@Range(min=0, max=10)
	private Long skillLevel;
	
	@Email
	private String email;
	
	@Past
	private Date born;

	public TestBean() {
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
	 * @return the lastaName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastaName the lastaName to set
	 */
	public void setLastName(String lastaName) {
		this.lastName = lastaName;
	}

	/**
	 * @return the age
	 */
	public Long getSkillLevel() {
		return skillLevel;
	}

	/**
	 * @param age the age to set
	 */
	public void setSkillLevel(Long age) {
		this.skillLevel = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the born
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * @param born the born to set
	 */
	public void setBorn(Date born) {
		this.born = born;
	}
}
