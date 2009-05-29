/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectionType {

	DrillInSelectionMode type() default DrillInSelectionMode.DROPDOWN;
	
}
