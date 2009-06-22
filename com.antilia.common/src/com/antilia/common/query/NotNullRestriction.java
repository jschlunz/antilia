package com.antilia.common.query;


public class NotNullRestriction implements IRestrictionFilter {

	private static final long serialVersionUID = 1L;

	private final String propertyName;

	public NotNullRestriction(String propertyName) {
		super();
		this.propertyName = propertyName;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}
	
}
