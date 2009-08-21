/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IComponent {

	public void render(HttpServletRequest request, PrintWriter writer);
	
	
	public String getId();

}
