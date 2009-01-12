/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.impl.TextField;
/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TextFieldFactory<B extends Serializable> implements IFieldFactory<B> {

	@SuppressWarnings("unchecked")
	private static TextFieldFactory instance;
	
	private TextFieldFactory() {		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#canHandleField(com.antilia.web.field.IFieldModel)
	 */
	public boolean canHandleField(IFieldModel<B> model, FieldMode mode) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel, FieldMode mode) {
		return new TextField<B>(id, fieldModel, mode);
	}

	@SuppressWarnings("unchecked")
	public static TextFieldFactory getInstance() {
		if(instance == null)
			instance = new TextFieldFactory();
		return instance;
	}

}
