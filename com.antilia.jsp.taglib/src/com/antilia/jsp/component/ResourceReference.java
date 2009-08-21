/**
 * 
 */
package com.antilia.jsp.component;

import org.apache.wicket.IClusterable;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ResourceReference implements IClusterable {

	private static final long serialVersionUID = 1L;

	
	private String scope;
		

	private String name;

	public ResourceReference(Class<?> scope, String name) {
		super();
		this.scope = scope.getName();
		this.name = name;
	}
	
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
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
		
		
}
