/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate;

import com.antilia.common.FrameworkException;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PersistenceException extends FrameworkException {

	private static final long serialVersionUID = 1L;

	public static String UNABLE_TO_SET_TIME_OUT 			= "PER001";
	public static String  KEY_VALUE_ILLEGAL_ACCESS 		= "PER002";
	public static String  LAZY_LOAD_FAILED 							= "PER003";	
	public static String  NO_PROPERTIES_TO_LOAD 			=  "PER004";
	public static String  SET_PROPERTY_VALUE					=  "PER005";
	public static String  COMMAND_EXECUTION					=  "PER005";
	
	
	public PersistenceException(String code) {
		this(code, code);
	}
	
	/**
	 * @param code
	 * @param message
	 */
	public PersistenceException(String code, String message) {
		super(code, message);
	}

	/**
	 * @param code
	 * @param e
	 */
	public PersistenceException(String code, Throwable e) {
		super(code, e);
	}

	/**
	 * @param code
	 * @param message
	 * @param e
	 */
	public PersistenceException(String code, String message, Throwable e) {
		super(code, message, e);
	}

}
