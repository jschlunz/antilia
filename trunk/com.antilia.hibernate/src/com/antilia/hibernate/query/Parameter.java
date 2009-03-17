/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

/**
 * Default implementation of Parameter.
 * 
 * @param <T> The type of the parameter.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Parameter<T> implements IParameter<T> {

	private String name;
	
	private T defaultValue;
	
	private Class<T> type;
	
	private T value;
	
	private boolean mandatory;
	
	public Parameter(String name, Class<T> type, T defaultValue, boolean mandatory) {
		this.type = type;
		this.defaultValue = defaultValue;
		this.mandatory = mandatory;
		if(!mandatory && this.defaultValue == null) {
			throw new IllegalArgumentException("For  non mandatory attributes it is required a default value");
		}
	}
	
	/**
	 * 
	 */
	public Parameter() {
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParameter#getDefaultValue()
	 */
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#getDefaultValue()
	 */
	public T getDefaultValue() {
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParameter#getName()
	 */
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParameter#getType()
	 */
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#getType()
	 */
	public Class<T> getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParameter#getValue()
	 */
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#getValue()
	 */
	public T getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParameter#isMandatory()
	 */
	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#isMandatory()
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#setDefaultValue(T)
	 */
	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#setType(java.lang.Class)
	 */
	public void setType(Class<T> type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#setValue(T)
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.antilia.hibernate.query.IParamter1#setMandatory(boolean)
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

}
