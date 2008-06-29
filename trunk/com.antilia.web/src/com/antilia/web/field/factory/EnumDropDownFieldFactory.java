/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.impl.EnumDropDownField;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EnumDropDownFieldFactory<B extends Serializable> implements IFieldFactory<B> {

	private static EnumDropDownFieldFactory<?> instance;
	
	private EnumDropDownFieldFactory() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#canHandleField(com.antilia.web.field.IFieldModel)
	 */
	public boolean canHandleField(IFieldModel<B> model) {		
		return model.getFieldClass().isEnum();
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel) {
		return new EnumDropDownField<B>(id, fieldModel);
	}

	@SuppressWarnings("unchecked")
	public static EnumDropDownFieldFactory getInstance() {
		if(instance == null)
			instance = new EnumDropDownFieldFactory();
		return instance;
	}
}
