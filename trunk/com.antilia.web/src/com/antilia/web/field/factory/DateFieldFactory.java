/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.Component;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.impl.DateField;
/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DateFieldFactory<B extends Serializable> implements IFieldFactory<B> {

	@SuppressWarnings("unchecked")
	private static DateFieldFactory instance;
	
	private DateFieldFactory() {		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#canHandleField(com.antilia.web.field.IFieldModel)
	 */
	public boolean canHandleField(IFieldModel<B> model) {
		return Date.class.isAssignableFrom(model.getFieldClass());
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel) {
		return new DateField<B>(id, fieldModel);
	}

	@SuppressWarnings("unchecked")
	public static DateFieldFactory getInstance() {
		if(instance == null)
			instance = new DateFieldFactory();
		return instance;
	}

}
