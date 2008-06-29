/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

import com.antilia.common.FrameworkException;

/**
 * OSGi related runtime exception.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class OsgiException extends FrameworkException {

	private static final long serialVersionUID = 1L;

	public static final String SERVICE_ACTIVATION_FAILURE  	= "AOSGI0001";
	public static final String SERVICE_TRACKER_FAILURE  	= "AOSGI0002";
	
	/**
	 * @param code
	 * @param message
	 */
	public OsgiException(String code, String message) {
		super(code, message);
	}

	/**
	 * @param code
	 * @param e
	 */
	public OsgiException(String code, Throwable e) {
		super(code, e);
	}

	/**
	 * @param code
	 * @param message
	 * @param e
	 */
	public OsgiException(String code, String message, Throwable e) {
		super(code, message, e);
	}

}
