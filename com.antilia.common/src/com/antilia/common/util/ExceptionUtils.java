package com.antilia.common.util;


import com.antilia.common.FrameworkException;

public class ExceptionUtils {

	/**
	 * returns a 'readable' messages for the exception <i>e</i>
	 * @param e
	 * @return Throwable
	 */
	public static String getExceptionMessage(Throwable e) {
		if (e instanceof ClassNotFoundException)
			return ("Class not found: " + e.getMessage());
		if (e instanceof NoSuchMethodException)
			return ("No such method: " + e.getMessage());
		if (e instanceof CloneNotSupportedException)
			return ("Clone not supported: " + e.getMessage());
		if (e instanceof FrameworkException)
			return (e.toString());
		if (e.getMessage() != null)
			return (e.getMessage());

		return (e.toString());
	}
	
	/**
	 * returns the root exception by looking for the final cause of the exception <i>e</i>
	 * @param e
	 * @return Throwable
	 */
	public static Throwable getRootCause(Throwable e) {
		if (e == null)
			return(null);
		while (e.getCause() != null)
			e = e.getCause();
		return(e);
	}
}
