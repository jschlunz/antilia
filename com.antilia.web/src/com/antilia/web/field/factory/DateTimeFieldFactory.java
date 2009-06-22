/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.wicket.Component;

import com.antilia.common.query.Operator;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.impl.DateTimeField;
/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DateTimeFieldFactory<B extends Serializable> implements IFieldFactory<B> {

	@SuppressWarnings("unchecked")
	private static DateTimeFieldFactory instance;
	
	private DateTimeFieldFactory() {		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#canHandleField(com.antilia.web.field.IFieldModel)
	 */
	public boolean canHandleField(IFieldModel<B> model, FieldMode mode) {
		return Timestamp.class.isAssignableFrom(model.getFieldClass());
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel, FieldMode mode) {
		fieldModel.setOperators(new Operator[]{
				Operator.EQUAL, 
				Operator.LESS_THAN, 
				Operator.LESS_EQUAL_THAN, 
				Operator.GREATER_THAN, 
				Operator.GREATER_EQUAL_THAN});
		if(fieldModel.getSelectedOperator()==null)
			fieldModel.setSelectedOperator(Operator.EQUAL);
		return new DateTimeField<B>(id, fieldModel,mode);
	}

	@SuppressWarnings("unchecked")
	public static DateTimeFieldFactory getInstance() {
		if(instance == null)
			instance = new DateTimeFieldFactory();
		return instance;
	}

}
