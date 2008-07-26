/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;

import org.apache.wicket.model.PropertyModel;

/**
 * This class represents a property Value.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PropertyValue<B extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String propertyName;
	
	private PropertyModel model;
	
	public PropertyValue(String propertyName, B bean) {		
		this.propertyName = propertyName;
		model = new PropertyModel(bean, propertyName);
	}

	/**
	 * @return the model
	 */
	public PropertyModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(PropertyModel model) {
		this.model = model;
	}

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
		
}
