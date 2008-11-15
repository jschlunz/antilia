/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;
import java.util.List;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Operator;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IAutoFieldCreator<B extends Serializable> extends Serializable {

	List<IFieldModel<B>> getFieldModels();
	
	void addFieldModel(IFieldModel<B> fieldModel);
	
	public IFieldModel<B> newFieldModel(String propertyPath);
	
	public IFieldModel<B> newFieldModel(String propertyPath, Operator[] operators, Operator selected);
	
	public IQuery<B> getFilterQuery();
	
}
