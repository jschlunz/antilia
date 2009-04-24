/**
 * 
 */
package com.antilia.web.field.impl;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.IModel;

import com.antilia.common.util.ResourceUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class EnumRadioChoice<T extends Enum<?>> extends TableRadioChoice<T> {

	private static final long serialVersionUID = 1L;

	public EnumRadioChoice(String id, IModel<T> model, Class<T> enumClass) {
		super(id, model, Arrays.asList(enumClass.getEnumConstants()));
		
		setChoiceRenderer(new ChoiceRenderer<T>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(T object) {
				if(object instanceof Enum)
					return ResourceUtils.getStringResource(object, getLocale());
				return super.getDisplayValue(object);
			}
		});
	}
	
}
