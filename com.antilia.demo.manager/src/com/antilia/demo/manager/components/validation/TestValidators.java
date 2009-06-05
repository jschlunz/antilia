/**
 * 
 */
package com.antilia.demo.manager.components.validation;

import java.util.Locale;

import org.hibernate.validator.InvalidValue;

import com.antilia.web.validation.ValidationUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class TestValidators {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestBean testBean = new TestBean();
		InvalidValue[] invalidValues = ValidationUtils.validateBeanAndReplaceLabels(testBean, null, Locale.getDefault());
		for(InvalidValue invalidValue: invalidValues) {
			System.out.println(invalidValue.getMessage());
		}
	}

}
