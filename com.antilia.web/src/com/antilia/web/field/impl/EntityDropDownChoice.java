/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.antilia.common.dao.IDaoLocator;
import com.antilia.common.dao.IQuerableDao;
import com.antilia.web.utils.DaoUtils;
import com.google.inject.Inject;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EntityDropDownChoice<B extends Serializable> extends DropDownChoice<B> {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	
	@Inject(optional=true)
	@SpringBean
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
		return DaoUtils.findQuerableDao(daoLocator, beanClass, null);
	}
}
