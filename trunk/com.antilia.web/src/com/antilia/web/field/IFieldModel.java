/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;

import com.antilia.hibernate.query.Operator;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IFieldModel<B extends Serializable> {
	
	String getPropertyPath();	
	void setPropertyPath(String propertyPath);
	
	Class<? extends B> getBeanClass();
	
	Class<?> getFieldClass();
	
	Operator getSelectedOperator();
	void setSelectedOperator(Operator operator);
	
	Operator[] getOperators();
	void setOperators(Operator[] operators);
	
}
