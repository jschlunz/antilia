/**
 * 
 */
package com.antilia.jsp.component;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface ILinkListener extends IActionListener {

	public void onLinkClicked(HttpServletRequest request, Writer writer);
	
}
