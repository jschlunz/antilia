/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.wstarter;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class OsgiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String SERVICE_ACTIVATION_FAILURE  = "ACOM0001";
	
	private String code;
	
	/**
	 * @param code
	 * @param message
	 */
	public OsgiException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * @param code
	 * @param e
	 */
	public OsgiException(String code, Throwable e) {
		super(e);
		this.code = code;
	}

	/**
	 * @param code
	 * @param message
	 * @param e
	 */
	public OsgiException(String code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
