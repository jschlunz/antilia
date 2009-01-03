package com.antilia.web.field;

import java.io.Serializable;

import javax.persistence.Column;

import com.antilia.common.util.AnnotationUtils;

/**
 * 
 * A configurator that uses Hibernate-JPA annotations to 
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HibernateJpaAutoFieldConfigurator<B extends Serializable> implements IAutoFieldConfigurator<B> {

	@Override
	public void configureFieldModel(IFieldModel<B> model) {
		try {
			Column column = AnnotationUtils.findFieldAnnotation(model.getBeanClass(), model.getPropertyPath(), Column.class);
			model.setLength(column.length());
			model.setRequiered(!column.nullable());
		} catch (Exception e) {
		
		}
	}
}
