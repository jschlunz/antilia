/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.jsp.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RequestContext {
	
	private static final ThreadLocal<RequestContext> current = new ThreadLocal<RequestContext>();

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private RequestContext() {		
	}
	
	public static RequestContext get() {		
		RequestContext requestContext = current.get();
		if(requestContext == null) {
			requestContext = new RequestContext();			
			current.set(requestContext);
		}		
		return requestContext;
 	}
	
	public static void unget() {
		current.remove();
	}
	
	public String urlFor(ResourceReference reference) {
		StringBuffer url = new StringBuffer();
		url.append(getRequest().getServletPath());
		url.append("/");
		url.append(HeaderContributor.RESOURCES_URI);
		url.append("/");
		url.append(reference.getScope());
		url.append("?res=");
		url.append(reference.getName());
		return url.toString();
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
