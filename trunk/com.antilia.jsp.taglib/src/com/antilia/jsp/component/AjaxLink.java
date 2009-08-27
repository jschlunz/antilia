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
public abstract class AjaxLink extends AbstractComponent implements ImenuItem, ILinkListener {

	/**
	 * @param id
	 */
	public AjaxLink(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.jsp.component.AbstractComponent#onRender(java.io.PrintWriter, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void onRender(PrintWriter writer, HttpServletRequest request) throws Exception {		
		writer.print("<a ");
		writer.print(" href=\"");
		writer.print(RequestContext.get().getUrlGenerator().generateUrlFor(this));
		writer.println("\">");
		writer.println(getId());
		writer.print(">");
	}

}
