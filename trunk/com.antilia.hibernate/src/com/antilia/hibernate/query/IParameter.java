/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.query;

/**
 * Interface that marks a parameter in an IQuery. That is an object that represents a value to
 * be determined. Use it to build parameterized queries.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IParameter<T> {


	/**
	 * The name of the parameter.
	 * @return
	 */
	public abstract String getName();

	/**
	 * The type of the parameter.s
	 * @return
	 */
	public abstract Class<T> getType();

	/**
	 * The actual value of the parameter.
	 * 
	 * @return
	 */
	public abstract T getValue();

	/**
	 * The default value of the parameter (it may be null if parameter is mandatory).
	 * 
	 * @return
	 */
	public abstract T getDefaultValue();
	
	/**
	 * Flag that determines whether the parameter is mandatory or not.
	 * 
	 * @return
	 */
	public abstract boolean isMandatory();

	/**
	 * @param name the name to set
	 */
	public abstract void setName(String name);

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public abstract void setDefaultValue(T defaultValue);

	/**
	 * @param type the type to set
	 */
	public abstract void setType(Class<T> type);

	/**
	 * @param value the value to set
	 */
	public abstract void setValue(T value);

	/**
	 * @param mandatory the mandatory to set
	 */
	public abstract void setMandatory(boolean mandatory);

}