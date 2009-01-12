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

	private static List<IFieldFactory> factories = new ArrayList<IFieldFactory>();
	
	static {
		factories.add(TextAreaFieldFactory.getInstance());
		factories.add(EnumDropDownFieldFactory.getInstance());
		factories.add(SelectionFieldFieldFactory.getInstance());
		factories.add(DateFieldFactory.getInstance());
		factories.add(LageSelectionFieldFactory.getInstance());
	}
	
	private static DefaultFieldFactory instance;
	/**
	 * 
	 */
	private DefaultFieldFactory() {
	
	}

	public boolean canHandleField(IFieldModel model, FieldMode mode) {
		return false;
	}

	public Component newField(String id, IFieldModel fieldModel, FieldMode mode) {
		for(IFieldFactory fieldFactory: factories) {
			if(fieldFactory.canHandleField(fieldModel, mode)) {
				return fieldFactory.newField(id, fieldModel, mode);
			}
		}
		return null;
	}
	
	public static DefaultFieldFactory getInstance() {
		if(instance == null)
			instance = new DefaultFieldFactory();
		return instance;
	}

}
