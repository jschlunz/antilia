/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import com.antilia.common.FrameworkException;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CommandExecutionException extends FrameworkException {

	private static final long serialVersionUID = 1L;

	public  static final String COMMAND_EXECUTION_EXCEPTION = "HPER0001";
	/**
	 * @param code
	 * @param message
	 */
	public CommandExecutionException(String code, String message) {
		super(code, message);
	}

	/**
	 * @param code
	 * @param e
	 */
	public CommandExecutionException(String code, Throwable e) {
		super(code, e);
	}

	/**
	 * @param code
	 * @param message
	 * @param e
	 */
	public CommandExecutionException(String code, String message, Throwable e) {
		super(code, message, e);
	}

}
