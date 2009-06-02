package com.antilia.web.validation;

import java.util.ResourceBundle;

import org.apache.wicket.Component;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.validation.IValidationError;
import org.hibernate.validator.ClassValidator;
import org.hibernate.validator.InvalidValue;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */

public class AnnotationsFormValidator<B> implements IFormValidator {

	private static final long serialVersionUID = 1L;
	
	private static class FormComponentVisitor implements Component.IVisitor<Component> {
	
		private String propertyName;
		
		private FormComponent<?> c;
		
		public FormComponentVisitor(String propertyName) {
			this.propertyName = propertyName;
		}
		
		public Object component(Component component) {
			if(component instanceof FormComponent<?>) {
				FormComponent<?> formComponent = (FormComponent<?>)component;
				if(formComponent.getId().equals(propertyName)) {
					c = formComponent;
					return IVisitor.STOP_TRAVERSAL;
				}
			}
			return IVisitor.CONTINUE_TRAVERSAL;
		}

		/**
		 * @return the c
		 */
		public FormComponent<?> getC() {
			return c;
		}
	}
	
	private Class<B> clazz;

	public AnnotationsFormValidator(Class<B> clazz) {
		this.clazz = clazz;
	}

	public AnnotationsFormValidator() {
	}

	public FormComponent<?>[] getDependentFormComponents() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public void validate(Form<?> form) {
		B object = (B)form.getModelObject();

		Class<B> _clazz = clazz == null ? (Class<B>)object.getClass() : clazz;

		ClassValidator<B> validator = new ClassValidator<B>(_clazz, ResourceBundle.getBundle(AnnotationBasedValidator.DEFAULT_VALIDATOR_MESSAGE));
		InvalidValue[] invalidValues = validator.getInvalidValues(object);
		for (InvalidValue iv : invalidValues) {
			FormComponent<?> component = findComponent(form, iv.getPropertyPath());
			if(component != null) {
				component.error((IValidationError)new org.apache.wicket.validation.ValidationError().setMessage(iv.getMessage()));
			} else
				form.error(new org.apache.wicket.validation.ValidationError().setMessage(iv.getMessage()));
		}
	}
	
	private FormComponent<?> findComponent(Form<?> form, final String propertyName) {
		FormComponentVisitor visitor = new FormComponentVisitor(propertyName);
		form.visitChildren(visitor);		
		return visitor.getC();
	}

}