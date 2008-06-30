/**
 * This software is private property of FCC (www.fcc.es).
 *
 */
package com.antilia.demo.manager;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (EReinaldoB@fcc.es)
 *
 */
public class ManagerSession extends WebSession {


	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 */
	public ManagerSession(Request request) {
		super(request);
	}
	
	public static ManagerSession getSession() {
		return (ManagerSession)Session.get();
	}

}
