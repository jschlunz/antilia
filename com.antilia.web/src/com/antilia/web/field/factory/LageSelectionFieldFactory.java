/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.factory;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	public boolean canHandleField(IFieldModel<B> model, FieldMode mode) {		
		try {
			if(isPreoperlyAnnotated(model)) {
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
	
	public boolean isPreoperlyAnnotated(IFieldModel<B> model) {
		try {
			if(AnnotationUtils.isFieldAnnotationPresent(model.getBeanClass(), model.getPropertyPath(), ManyToOne.class))
				return true;
			if(AnnotationUtils.isFieldAnnotationPresent(model.getBeanClass(), model.getPropertyPath(), OneToOne.class) 
					&&  AnnotationUtils.isFieldAnnotationPresent(model.getBeanClass(), model.getPropertyPath(), JoinColumn.class))
				return true;
		} catch (Exception e) {			
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.factory.IFieldFactory#newField(java.lang.String, com.antilia.web.field.IFieldModel)
	 */
	public Component newField(String id, IFieldModel<B> fieldModel, FieldMode mode) {
		return new LargeSelectionField<B>(id, fieldModel, mode);
	}

	@SuppressWarnings("unchecked")
	public static LageSelectionFieldFactory getInstance() {
		if(instance == null)
			instance = new LageSelectionFieldFactory();
		return instance;
	}
}
