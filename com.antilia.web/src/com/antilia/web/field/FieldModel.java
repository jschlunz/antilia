/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;

import org.apache.wicket.model.Model;

import com.antilia.common.util.ReflectionUtils;
import com.antilia.common.util.StringUtils;
import com.antilia.hibernate.query.Operator;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class FieldModel<B extends Serializable>  extends Model<B> implements IFieldModel<B> {

	private static final long serialVersionUID = 1L;

	private Class<? extends B> beanClass;
	
	private Operator[] operators;
	
	private String propertyPath;
	
	private Operator selectedOperator;

	private Class<?> fieldClass;
	
	private int length = 40;	
	
	private boolean requiered;
	
	private BeanProxy<B> beanProxy;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public FieldModel(BeanProxy<B> beanProxy, String propertyPath) {		
		super();
		if(beanProxy == null)
			throw new IllegalArgumentException("Bean proxy cannot be null");		
		this.beanProxy = beanProxy;		 
		this.beanProxy.addPropertyValue(propertyPath, new PropertyValue(propertyPath, beanProxy.getBean()));
		
		this.beanClass = beanProxy.getBeanClass();
		if(StringUtils.isEmpty(propertyPath)) 
			throw new IllegalArgumentException("PropertyPath class cannot be null");
		this.propertyPath = propertyPath;		
		
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IFieldModel#getBeanClass()
	 */
	public Class<? extends B> getBeanClass() {
		return beanClass;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IFieldModel#getOperators()
	 */
	public Operator[] getOperators() {
		return operators;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IFieldModel#getPropertyPath()
	 */
	public String getPropertyPath() {
		return propertyPath;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IFieldModel#getSelectedOperator()
	 */
	public Operator getSelectedOperator() {
		return selectedOperator;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IFieldModel#setOperators(com.antilia.persistence.filter.Operator[])
	 */
	public void setOperators(Operator[] operators) {
		this.operators = operators;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IFieldModel#setSelectedOperator(com.antilia.persistence.filter.Operator)
	 */
	public void setSelectedOperator(Operator operator) {		
		this.selectedOperator = operator;
	}

	public void setBeanClass(Class<B> beanClass) {
		this.beanClass = beanClass;
	}

	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	public Class<?> getFieldClass() {
		if(fieldClass == null) {
			fieldClass = ReflectionUtils.getPropertyClass(getBeanClass(), getPropertyPath());
		}
		return fieldClass;
	}

	/**
	 * @return the beanProxy
	 */
	public BeanProxy<B> getBeanProxy() {
		return beanProxy;
	}

	/**
	 * @param beanProxy the beanProxy to set
	 */
	public void setBeanProxy(BeanProxy<B> beanProxy) {
		this.beanProxy = beanProxy;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isRequiered() {
		return requiered;
	}

	public void setRequiered(boolean requiered) {
		this.requiered = requiered;
	}

}
