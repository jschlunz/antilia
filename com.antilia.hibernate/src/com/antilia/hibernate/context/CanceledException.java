/**
 * 
 */
package com.antilia.hibernate.context;

import com.antilia.common.FrameworkException;

/**
 * @author reiern70@gmail.com
 *
 */
public class CanceledException extends FrameworkException {

	 private static final long serialVersionUID = 1L;

	/**
	 * @param code
	 * @param message
	 */
	public CanceledException(String code, String message) {
		super(code, message);
	}

	/**
	 * @param code
	 * @param e
	 */
	public CanceledException(String code, Throwable e) {
		super(code, e);
	}

	/**
	 * @param code
	 * @param message
	 * @param e
	 */
	public CanceledException(String code, String message, Throwable e) {
		super(code, message, e);
	}

}
