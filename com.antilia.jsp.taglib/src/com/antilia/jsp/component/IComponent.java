/**
 * 
 */
package com.antilia.jsp.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IComponent {

	public void render(HttpServletRequest request, HttpServletResponse response);
}
