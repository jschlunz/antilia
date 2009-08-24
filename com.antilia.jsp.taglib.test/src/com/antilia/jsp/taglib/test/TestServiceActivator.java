/**
 * 
 */
package com.antilia.jsp.taglib.test;

import com.antilia.web.osgi.ServletServiceActivator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class TestServiceActivator extends ServletServiceActivator<TestServlet> {

	protected TestServlet getServlet() {
		return new TestServlet();
	};
	
	@Override
	protected String getServletAlias() {
		return "test";			
	}
	
	public boolean isMandatory() throws Exception {
		return false;
	}
}
