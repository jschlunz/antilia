/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;

import com.antilia.web.field.IFieldModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
@SuppressWarnings("unchecked")
public class DefaultFieldFactory implements IFieldFactory {

	@SuppressWarnings("unchecked")
	private static List<IFieldFactory> factories = new ArrayList<IFieldFactory>();
	
	static {
		factories.add(EnumDropDownFieldFactory.getInstance());
		factories.add(SelectionFieldFieldFactory.getInstance());
	}
	
	private static DefaultFieldFactory instance;
	/**
	 * 
	 */
	private DefaultFieldFactory() {
	
	}

	public boolean canHandleField(IFieldModel model) {
		return false;
	}

	public Component newField(String id, IFieldModel fieldModel) {
		for(IFieldFactory fieldFactory: factories) {
			if(fieldFactory.canHandleField(fieldModel)) {
				return fieldFactory.newField(id, fieldModel);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static DefaultFieldFactory getInstance() {
		if(instance == null)
			instance = new DefaultFieldFactory();
		return instance;
	}

}
