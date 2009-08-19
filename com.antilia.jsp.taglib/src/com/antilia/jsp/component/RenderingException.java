/**
 * 
 */
package com.antilia.jsp.component;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class RenderingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public RenderingException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public RenderingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RenderingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RenderingException(String message, Throwable cause) {
		super(message, cause);
	}

}
