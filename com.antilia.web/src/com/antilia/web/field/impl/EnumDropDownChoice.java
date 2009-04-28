/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;

import com.antilia.common.util.ResourceUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EnumDropDownChoice<T extends Enum<?>> extends DropDownChoice<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param enumClass
	 * @param model
	 */
	public EnumDropDownChoice(String id, Class<T> enumClass ,IModel<T> model) {
		this(id, Arrays.asList(enumClass.getEnumConstants()), model);				
	}
	
	/**
	 * 
	 * @param id
	 * @param choices
	 * @param model
	 */
	public EnumDropDownChoice(String id, List<T> choices,IModel<T> model) {
		super(id);
		setModel(model);
		setNullValid(true);
		setChoiceRenderer(new ChoiceRenderer<T>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(T object) {
				if(object instanceof Enum)
					return ResourceUtils.getStringResource(object, getLocale());
				return super.getDisplayValue(object);
			}
		});		
		setChoices(choices);
	}
}
