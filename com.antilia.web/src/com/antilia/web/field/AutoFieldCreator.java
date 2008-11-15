/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.Model;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Operator;
import com.antilia.hibernate.query.Query;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AutoFieldCreator<B extends Serializable> extends Model<BeanProxy<B>> implements IAutoFieldCreator<B> {

	private static final long serialVersionUID = 1L;
	
	private List<IFieldModel<B>> models = new ArrayList<IFieldModel<B>>();
		
	private IQuery<B> filterQuery;
	
	/**
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	public AutoFieldCreator(B object) {
		super(new BeanProxy<B>(object));
		this.filterQuery = new Query<B>((Class<B>)object.getClass());
	}
	
	/**
	 * 
	 */
	public AutoFieldCreator(IQuery<B> filterQuery, BeanProxy<B> beanProxy) {
		super();
		this.filterQuery = filterQuery;		
		setObject(beanProxy);
	}

	public BeanProxy<B> getBeanProxy() {
		return (BeanProxy<B>)getObject();
	}
	
	

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IAutoFieldModel#getFieldModels()
	 */
	public List<IFieldModel<B>> getFieldModels() {
		return models;
	}
	
	@Override
	public void addFieldModel(IFieldModel<B> fieldModel) {
		models.add(fieldModel);
	}
	
	public IFieldModel<B> newFieldModel(String propertyPath) {
		FieldModel<B> model = new FieldModel<B>(getBeanProxy(), propertyPath);			
		configureFieldModel(model);
		models.add(model);
		return model;
	}
	
	public IFieldModel<B> newFieldModel(String propertyPath, Operator[] operators, Operator selected) {
		IFieldModel<B> model = newFieldModel(propertyPath);
		model.setOperators(operators);
		model.setSelectedOperator(selected);
		models.add(model);		
		return model;
	}
	
	private void configureFieldModel(IFieldModel<B> model) {
		
	}
	
	public B getBean() {
		return getObject().getBean();
	}
	
	@SuppressWarnings("unchecked")
	public Class<? extends B> getBeanClass() {
		return (Class<? extends B>)getBean().getClass();
	}

	/**
	 * @return the filterQuery
	 */
	public IQuery<B> getFilterQuery() {
		return filterQuery;
	}

	/**
	 * @param filterQuery the filterQuery to set
	 */
	public void setFilterQuery(IQuery<B> filterQuery) {
		this.filterQuery = filterQuery;
	}
	
}
