/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.util.lang.PropertyResolver;

import com.antilia.common.query.IQuery;
import com.antilia.common.query.Operator;
import com.antilia.common.query.Restrictions;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class BeanProxy<B extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Map<String, PropertyValue<? extends Serializable>> propertyValues = new HashMap<String, PropertyValue<? extends Serializable>>();
	
	private Class<? extends B> beanClass;
	
	private List<B> beans = new ArrayList<B>(); 
	
	public BeanProxy(Class<? extends B> beanClass) {
		this.beanClass = beanClass;
		try {
			this.beans.add(beanClass.newInstance());
		} catch (Exception e) {
		}
	}
	
	@SuppressWarnings("unchecked")
	public BeanProxy(B bean) {
		this.beanClass = (Class<? extends B>)bean.getClass();
		try {
			this.beans.add(bean);
		} catch (Exception e) {
		}
	}
	
	public void addPropertyValue(String propertyName, PropertyValue<?> value) {
		propertyValues.put(propertyName, value);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> PropertyValue<T> getPropertyValue(String propertyName) {
		return (PropertyValue<T>)propertyValues.get(propertyName);
	}
	
	/**
	 * @return the beanClass
	 */
	public Class<? extends B> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<? extends B> beanClass) {
		this.beanClass = beanClass;
	}	

	/**
	 * 
	 * @param query
	 */
	public void updateFilterQuery(IQuery<B> query, Map<String, IFieldModel<B>> models) {
		query.clearRestrictions();
		for(String propertyName: propertyValues.keySet()) {			
			Object value = PropertyResolver.getValue(propertyName, getBean());			
			IFieldModel<B> model = models.get(propertyName);
			Operator operator = null;
			if(model != null) {
				operator = model.getSelectedOperator();
			}
			if(value != null) {
				if(value instanceof String) {
					if(operator == null || operator.equals(Operator.EQUAL))
						query.addRestriction(Restrictions.eq(propertyName, ((String)value).trim()));
					else if(operator.equals(Operator.LIKE) || operator.equals(Operator.ILIKE)) 	
						query.addRestriction(Restrictions.ilike(propertyName, value));		
					else 
						query.addRestriction(Restrictions.eq(propertyName, value));
				} else if(value instanceof Date) {
					Date date = (Date) value;
					if(operator == null || operator.equals(Operator.EQUAL))
						query.addRestriction(Restrictions.eq(propertyName, date));
					else if(operator.equals(Operator.LESS_THAN)) 	
						query.addRestriction(Restrictions.lt(propertyName, date));
					else if(operator.equals(Operator.LESS_EQUAL_THAN)) 	
						query.addRestriction(Restrictions.le(propertyName, date));
					else if(operator.equals(Operator.GREATER_THAN)) 	
						query.addRestriction(Restrictions.gt(propertyName, date));
					else if(operator.equals(Operator.GREATER_EQUAL_THAN)) 	
						query.addRestriction(Restrictions.ge(propertyName, date));
					else 
						query.addRestriction(Restrictions.eq(propertyName, value));
				} else {
					query.addRestriction(Restrictions.eq(propertyName, value));
				}
			}
		}
	}
	
	public B getBean() {
		return beans.get(0);
	}

}
