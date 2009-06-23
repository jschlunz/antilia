package com.antilia.ibatis;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Annotation used to convey information 
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
@Inherited
@Documented
public @interface Column {

	/**
	 * 
	 * @return
	 */
	String name() default "";
	
	/**
	 * 
	 * @return
	 */
	boolean defaultorder() default false;
	
    
}
