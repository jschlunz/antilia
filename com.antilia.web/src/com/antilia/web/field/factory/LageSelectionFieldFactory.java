/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;

import javax.persistence.ManyToOne;

import org.apache.wicket.Component;

import com.antilia.common.util.AnnotationUtils;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.impl.LargeSelectionField;
import com.antilia.web.field.impl.SelectionMode;
import com.antilia.web.field.impl.SelectionType;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LageSelectionFieldFactory<B extends Serializable> implements IFieldFactory<B> {

	private static LageSelectionFieldFactory<?> instance;
	
	private LageSelectionFieldFactory() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#canHandleField(com.antilia.web.field.IFieldModel)
	 */
	public boolean canHandleField(IFieldModel<B> model) {		
		try {
			if(AnnotationUtils.isFieldAnnotationPresent(model.getBeanClass(), model.getPropertyPath(), ManyToOne.class) ) {
				if(AnnotationUtils.isFieldAnnotationPresent(model.getBeanClass(), model.getPropertyPath(), SelectionType.class)) {
					SelectionType selectionType = AnnotationUtils.findFieldAnnotation(model.getBeanClass(), model.getPropertyPath(), SelectionType.class);
					if(selectionType != null) {
						return selectionType.type().equals(SelectionMode.LARGE_IN_MODAL_DIALOG) || selectionType.type().equals(SelectionMode.LARGE_ON_NEXT_PAGE);
					}
				}
				return false;
			}				
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel) {
		return new LargeSelectionField<B>(id, fieldModel);
	}

	@SuppressWarnings("unchecked")
	public static LageSelectionFieldFactory getInstance() {
		if(instance == null)
			instance = new LageSelectionFieldFactory();
		return instance;
	}
}
