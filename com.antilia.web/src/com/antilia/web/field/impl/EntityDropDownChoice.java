/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;

import com.antilia.hibernate.dao.IDaoLocator;
import com.antilia.hibernate.dao.IQuerableDao;
import com.antilia.hibernate.dao.impl.DefaultDaoLocator;
import com.google.inject.Inject;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EntityDropDownChoice<B extends Serializable> extends DropDownChoice<B> {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	
	@Inject(optional=true)
	private transient IDaoLocator daoLocator;
	
	/**
	 * @param id
	 */
	public EntityDropDownChoice(String id, Class<B> beanClass ,IModel<B> model) {
		super(id);		
		setModel(model);
		this.beanClass = beanClass;
		setNullValid(true);		
		setChoiceRenderer(new ChoiceRenderer<B>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(B object) {
				if(object != null && EntityDropDownChoice.this.beanClass.isAssignableFrom(object.getClass()))
					return object.toString();				
				return super.getDisplayValue(object);
			}
		});	
				
		IQuerableDao<B> querableDao  = createQuerableDao(beanClass);
		setChoices(querableDao.findAll(beanClass));	
		//setChoices(DefaultCommander.loadAll(beanClass));
	}
	
	/**
	 * 
	 * @return
	 */
	protected IQuerableDao<B> createQuerableDao(Class<B> beanClass) {
		try {
			return daoLocator.locateQuerableDao(beanClass, null);
		}catch (Exception e) {
			// do nothing
		}		
		daoLocator = DefaultDaoLocator.getInstance();
		return daoLocator.locateQuerableDao(beanClass, null);
	}
}
