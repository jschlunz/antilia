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
public class MenuComponent extends AbstractCompoundComponent {

	/**
	 * @param id
	 */
	public MenuComponent(String id) {
		super(id);
	}


	/* (non-Javadoc)
	 * @see com.antilia.jsp.component.AbstractComponent#onRender(java.io.PrintWriter, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void onRender(PrintWriter writer, HttpServletRequest request) throws Exception {
		writer.println("<div class=\"nav-menu\" style=\"\">");
		writer.println("<div class=\"menubody\">");	
		writer.println("<ul>");
		for(IComponent component: getChildrem()) {
			writer.println("<li>");
			component.render(request, writer);
			writer.println("</li>");
		}				
		writer.println("</ul>");
		writer.println("</div>");
		writer.println("</div>");
	}

	public void addMenuItem(ImenuItem item) {
		addComponent(item);
	}
	
	@Override
	public void addComponent(IComponent component) {
		if(component instanceof ImenuItem)
			super.addComponent(component);
	}
}
