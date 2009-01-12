/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import com.antilia.common.util.AnnotationUtils;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LargeSelectionField<B extends Serializable> extends BaseFormField<B> {

	private static final long serialVersionUID = 1L;

	private Label textField;
	
	/**
	 * @param id
	 * @param model
	 */
	public LargeSelectionField(String id, IFieldModel<B> model, FieldMode mode) {
		super(id, model, mode);
		
		label = new Label("label", getLabelModel());
		add(label);
						
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		if(textField == null) {
			textField = new Label(
				"field", 
				getBeanProxy().getPropertyValue(getPropertyPath()).getModel());
			add(textField);
		}		
		try {
			SelectionType selectionType = AnnotationUtils.findFieldAnnotation(getFieldModel().getBeanClass(), getFieldModel().getPropertyPath(), SelectionType.class);
			if(selectionType.type().equals(SelectionMode.LARGE_IN_MODAL_DIALOG) )				
				add(new LargeSelectionDialogButton<B>("selectionPanel", getBeanProxy(), getFieldModel()));
			else if(selectionType.type().equals(SelectionMode.LARGE_ON_NEXT_PAGE)) {
				add(new InPlaceLargeSelectionButton<B>("selectionPanel", getBeanProxy(), getFieldModel()));
			}
		} catch (Exception e) {
			add(new Label("selectionPanel", e.getMessage()));
		}
		if(getMode() == FieldMode.EDIT && getFieldModel().isRequiered()) {
			// if we are in EDIT mode and field is required then do not allow to get rid of selection
			// so that user is forced to select one.
			add(new Label("dropSelection",new Model<String>("")));
		} else
			add(new DropSelectionButton("dropSelection",getBeanProxy(), getFieldModel()));
	}

}
