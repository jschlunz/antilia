/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AutoCrudStyler<E extends Serializable> extends CrudStyler<E> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param beanClass
	 */
	public AutoCrudStyler(Class<E> beanClass) {
		super(beanClass);
		init(beanClass);
	}

	/**
	 * Uses reflection to gather information of the fields.
	 * 
	 * @param beanClass
	 */
	private void init(Class<E> beanClass) {		
		List<String> fieldNames = new ArrayList<String>();
		
		Field[] fields  = beanClass.getDeclaredFields();		
		for(Field field: fields) {			
			if(!rejectField(field)) 
				fieldNames.add(field.getName());
		}		
		addEditFields(fieldNames);
		addSearchFields(fieldNames);		
		addTableColumns(fieldNames);
	}
	
	private boolean rejectField(Field field) {
		int modifiers = field.getModifiers();
		if(Modifier.isStatic(modifiers))
			return true;		
		if(field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class))
			return true;
		return false;
	}
}
