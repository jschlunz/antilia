/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common;

/**
 * Framework's base runtime exception.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FrameworkException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	

	/**
	 * @param arg0
	 */
	public FrameworkException(String code, String message) {
		super(message);		
		this.code = code;
	}

	/**
	 * @param arg0
	 */
	public FrameworkException(String code, Throwable e) {
		super(e);
		this.code = code;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FrameworkException(String code, String message, Throwable e) {
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
