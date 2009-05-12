/**
 * 
 */
package com.antilia.web.beantable.model;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation can be used to convey extra information regarding 
 * BeanTable's column properties. They should be applied to 
 * fields or getter methods.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
@Target(FIELD) 
@Retention(RUNTIME)
public @interface TableColumn {

	/**
	 * The width of a column.
	 * @return
	 */
	int width() default 200; 
	
	/**
	 * @return If columns is sortable or not.
	 */
	boolean sortable() default true; 
	
	
	/**
	 * @return Whether the column is resizable of not.
	 */
	boolean resizable() default true; 
	
}
