/**
 * 
 */
package com.antilia.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

/**
 * @author reiern70@gmail.com
 */
public abstract class AntiliaSession extends WebSession implements IAuthenticableSession {

	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 */
	public AntiliaSession(Request request) {
		super(request);
	}

		
}
