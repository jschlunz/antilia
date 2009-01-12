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
@SuppressWarnings("unchecked")
public class EnumDropDownChoice<T extends Enum> extends DropDownChoice<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public EnumDropDownChoice(String id, Class<T> enumClass ,IModel<T> model) {
		super(id);		
		setModel(model);
		setNullValid(true);
		setChoiceRenderer(new ChoiceRenderer() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Object object) {
				if(object instanceof Enum)
					return ResourceUtils.getStringResource((Enum)object, getLocale());
				return super.getDisplayValue(object);
			}
		});		
		List<T> choises = Arrays.asList(enumClass.getEnumConstants());
		setChoices(choises);
	}
}
