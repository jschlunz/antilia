/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.impl.TextAreaField;
/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TextAreaFieldFactory<B extends Serializable> implements IFieldFactory<B> {

	@SuppressWarnings("unchecked")
	private static TextAreaFieldFactory instance;
	
	private TextAreaFieldFactory() {		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#canHandleField(com.antilia.web.field.IFieldModel)
	 */
	public boolean canHandleField(IFieldModel<B> model) {
		if(String.class.isAssignableFrom(model.getFieldClass()) && model.getLength() > 80)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel) {
		return new TextAreaField<B>(id, fieldModel);
	}

	@SuppressWarnings("unchecked")
	public static TextAreaFieldFactory getInstance() {
		if(instance == null)
			instance = new TextAreaFieldFactory();
		return instance;
	}

}
