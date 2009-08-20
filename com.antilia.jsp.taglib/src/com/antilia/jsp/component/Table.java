/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 
 */
public class Table<B extends Serializable> extends AbstractComponent {
	
	private Class<B> beanClass;
	
	
	@Override
	protected void onRender(PrintWriter writer, HttpServletRequest request) throws Exception {
		writer.write("<table cellpadding=\"0\" cellspacing=\"0\" class=\"tbody\">");
		writer.write("</table>");
	}

	/**
	 * @return the beanClass
	 */
	public Class<B> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<B> beanClass) {
		this.beanClass = beanClass;
	}

}
