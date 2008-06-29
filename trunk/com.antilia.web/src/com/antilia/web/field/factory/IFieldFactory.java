/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.field.IFieldModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IFieldFactory<B extends Serializable> {

	boolean canHandleField(IFieldModel<B> model);
	
	Component newField(String id, IFieldModel<B> fieldModel);
}
