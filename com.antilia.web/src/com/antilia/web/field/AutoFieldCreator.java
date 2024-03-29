/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.model.Model;

import com.antilia.common.query.IQuery;
import com.antilia.common.query.Operator;
import com.antilia.common.query.Query;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AutoFieldCreator<B extends Serializable> extends Model<BeanProxy<B>> implements IAutoFieldCreator<B> {

	private static final long serialVersionUID = 1L;
	
	private Map<String, IFieldModel<B>> models; 
		
	private IQuery<B> filterQuery;
	
	private IAutoFieldConfigurator<B> autoFieldConfigurator = new HibernateJpaAutoFieldConfigurator<B>();
	
	/**
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	public AutoFieldCreator(B object) {
		super(new BeanProxy<B>(object));
		this.filterQuery = new Query<B>((Class<B>)object.getClass());
		this.models = new HashMap<String,IFieldModel<B>>();
	}
	
	public AutoFieldCreator(IQuery<B> filterQuery, BeanProxy<B> beanProxy) {
		this(filterQuery, beanProxy, new HashMap<String,IFieldModel<B>>());
	}
	
	/**
	 * 
	 */
	public AutoFieldCreator(IQuery<B> filterQuery, BeanProxy<B> beanProxy, Map<String,IFieldModel<B>> models) {
		super();
		this.filterQuery = filterQuery;		
		this.models = models;
		setObject(beanProxy);
	}

	public BeanProxy<B> getBeanProxy() {
		return (BeanProxy<B>)getObject();
	}
	
	

	/* (non-Javadoc)
	 * @see com.antilia.web.field.IAutoFieldModel#getFieldModels()
	 */
	public Map<String,IFieldModel<B>> getFieldModels() {
		return models;
	}
	
	public void addFieldModel(IFieldModel<B> fieldModel) {
		models.put(fieldModel.getPropertyPath(), fieldModel);
	}
	
	public IFieldModel<B> newFieldModel(String propertyPath) {
		FieldModel<B> model = new FieldModel<B>(getBeanProxy(), propertyPath);			
		configureFieldModel(model);
		models.put(model.getPropertyPath(), model);
		return model;
	}
	
	public IFieldModel<B> newFieldModel(String propertyPath, Operator[] operators, Operator selected) {
		IFieldModel<B> model = newFieldModel(propertyPath);
		model.setOperators(operators);
		model.setSelectedOperator(selected);
		models.put(model.getPropertyPath(),model);		
		return model;
	}
		
	private void configureFieldModel(IFieldModel<B> model) {
		getAutoFieldConfigurator().configureFieldModel(model);
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

	public IAutoFieldConfigurator<B> getAutoFieldConfigurator() {
		return autoFieldConfigurator;
	}

	public void setAutoFieldConfigurator(
			IAutoFieldConfigurator<B> autoFieldConfigurator) {
		this.autoFieldConfigurator = autoFieldConfigurator;
	}
	
}
