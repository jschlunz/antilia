/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractComponent implements IComponent {

	/* (non-Javadoc)
	 * @see com.antilia.jsp.component.IComponent#render(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void render(HttpServletRequest request, HttpServletResponse response) {
		try {
			onRender(response.getWriter(), request);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 
	 * @param writer
	 * @param request
	 * @throws Exception
	 */
	protected abstract void onRender(PrintWriter writer, HttpServletRequest request) throws Exception; 
	

}
