/**
 * 
 */
package com.antilia.web.validation;

import java.util.ResourceBundle;

import org.apache.wicket.util.lang.PropertyResolver;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AnnotationBasedValidator<B,T> extends AbstractValidator<T> {

	private static final long serialVersionUID = 1L;

	private Class<B> clazz;
	
	private String propertyPath;
	
	public static final String DEFAULT_VALIDATOR_MESSAGE = AnnotationBasedValidator.class.getName();
	
	/**
	 * 
	 */
	public AnnotationBasedValidator(Class<B> clazz, String propertyPath) {
		this.clazz = clazz;
		this.propertyPath = propertyPath;
	}

	@Override
	public boolean validateOnNullValue() {
		return true;
	}
	
	@Override
	protected void onValidate(IValidatable<T> validatable) {
		B object = null;
		if (object == null) {
			try {
				object = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			PropertyResolver.setValue(propertyPath, object, validatable.getValue(), null);
		}

		ClassValidator<B> validator = new ClassValidator<B>(clazz, ResourceBundle.getBundle(DEFAULT_VALIDATOR_MESSAGE));
		InvalidValue[] invalidValues = validator.getInvalidValues(object,
						propertyPath);

		for (org.hibernate.validator.InvalidValue iv : invalidValues)
			validatable.error(new org.apache.wicket.validation.ValidationError().setMessage(iv.getMessage()));
	}


	/**
	 * @return the clazz
	 */
	public Class<B> getClazz() {
		return clazz;
	}


	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class<B> clazz) {
		this.clazz = clazz;
	}


	/**
	 * @return the propertyPath
	 */
	public String getPropertyPath() {
		return propertyPath;
	}


	/**
	 * @param propertyPath the propertyPath to set
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}
	
	
}
