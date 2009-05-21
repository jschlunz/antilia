/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;

import com.antilia.hibernate.command.DefaultCommander;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EntityDownChoice<B extends Serializable> extends DropDownChoice<B> {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	/**
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public EntityDownChoice(String id, Class<B> beanClass ,IModel model) {
		super(id);		
		setModel(model);
		this.beanClass = beanClass;
		setNullValid(true);		
		setChoiceRenderer(new ChoiceRenderer() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Object object) {
				if(object != null && EntityDownChoice.this.beanClass.isAssignableFrom(object.getClass()))
					return object.toString();				
				return super.getDisplayValue(object);
			}
		});	
				
		setChoices(DefaultCommander.loadAll(beanClass));
	}
}
