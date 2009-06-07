/**
 * 
 */
package com.antilia.web.validation;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.wicket.util.string.interpolator.PropertyVariableInterpolator;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;
import org.hibernate.validator.Validator;
import org.hibernate.validator.ValidatorClass;

import com.antilia.common.util.ResourceUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ValidationUtils {
	
	public static class MyInvalidValue extends InvalidValue {
		
		private static final long serialVersionUID = 1L;
		
		private String message;				

		public MyInvalidValue(InvalidValue invalidValue) {
			super(invalidValue.getMessage(), invalidValue.getBeanClass(), invalidValue.getPropertyName(), invalidValue.getValue(), invalidValue.getBean());
		}
		
		public MyInvalidValue(String message, Class<?> beanClass, String propertyName, Object value, Object bean) {
			super(message, beanClass, propertyName, value, bean);
			
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	private static class LabelHolder implements Serializable {
		
		private static final long serialVersionUID = 1L;

		public LabelHolder(String label) {
			super();
			this.label = label;
		}

		private String label;

		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}
		
	}
	
	public static class ValidatorsReosurceBundle<B> extends ResourceBundle {
		
		private Class<B> beanClass;
		
		private Set<String> validatorPackages = new HashSet<String>();
		
		public ValidatorsReosurceBundle(Class<B> beanClass) {
			this.beanClass = beanClass;
			intitialize(this.beanClass);
		}
		
		@SuppressWarnings("unchecked")
		private void intitialize(Class<B> beanClass) {
			if(beanClass == null)
				throw new IllegalArgumentException("Bean class cannot be null");
			Annotation[] annotations = beanClass.getAnnotations();
			for(Annotation annotation: annotations) {
				ValidatorClass validatorClassAn = annotation.getClass().getAnnotation(ValidatorClass.class);
				if(validatorClassAn != null) {
					// this is a validator associated annotation.
					Class<? extends Validator> validatorClass = validatorClassAn.value();
					if(validatorClass != null) {
						String packageName = validatorClass.getPackage().getName();
						if(!validatorPackages.contains(packageName))
							validatorPackages.add(packageName);
					}
					
				}
			}
		}

		@Override
		public Enumeration<String> getKeys() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Object handleGetObject(String key) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * Validates a bean and returns a list of Invalid Values with no {label} replacement.
	 * 
	 * @param <T>
	 * @param bean
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  static <T> InvalidValue[] validateBean(T bean, Class<T> clazz) {
		Class<T> _clazz = clazz == null ? (Class<T>)bean.getClass() : clazz;
		ClassValidator<T> validator = new ClassValidator<T>(_clazz, 
				ResourceBundle.getBundle(AnnotationBasedValidator.DEFAULT_VALIDATOR_MESSAGE));
		InvalidValue[] invalidValues = validator.getInvalidValues(bean);
		return invalidValues;
	}
	
	
	public  static <T> InvalidValue[] validateBeanAndReplaceLabels(T bean, Class<T> clazz, Locale locale) {
		InvalidValue[] invalidValues = validateBean(bean, clazz);		
		MyInvalidValue[] translatedMessages = new MyInvalidValue[invalidValues.length];
		int count = 0;
		for(InvalidValue invalidValue: invalidValues) {
			String key = ResourceUtils.getPropertyResourceKey(invalidValue.getBeanClass(), invalidValue.getPropertyPath());
			String label = ResourceUtils.getStringResource(invalidValue.getBeanClass(), key, locale, (Object[])null);
			String string = invalidValue.getMessage();
			string = PropertyVariableInterpolator.interpolate(string, new LabelHolder(label));	
			MyInvalidValue invalidValue2 = new MyInvalidValue(invalidValue);
			invalidValue2.setMessage(string);
			translatedMessages[count] = invalidValue2;
			count++;
		}	
		return translatedMessages;
	}

}
